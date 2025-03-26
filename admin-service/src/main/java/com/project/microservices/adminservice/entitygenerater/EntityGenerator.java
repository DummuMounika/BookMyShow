package com.project.microservices.adminservice.entitygenerater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class EntityGenerator {
	public static Statement stmt;
	public static Connection con;
	public static BufferedWriter bw;
	public static String primaryKey;
	public static String sequenceConstraint;
	public static String sequenceColumn;
	public static String listOfTables;
	
	/**
	 * Constructor with database connection
	 */
	public EntityGenerator() {
		try {
			System.out.println("started");
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/newbookmyshow", "postgres", "123456789");
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		listOfTables = "\'theaterseats\'";
		EntityGenerator entityGenerator = new EntityGenerator();
//		entityGenerator.getTablesList();
		entityGenerator.createEntityFiles(listOfTables);
	}

	/*private void getTablesList() throws Exception {
		String qry = "select table_name from information_schema.tables where table_schema = 'public' and table_type ilike '%BASE TABLE%'";
		ResultSet rst = stmt.executeQuery(qry);
		while (rst.next()) {
			listOfTables = "\'" + rst.getString(1) + "\'" + listOfTables;
		}
	}*/

	/**
	 * Method to create the file
	 * @param listOfTables
	 * @throws Exception
	 */
	private void createEntityFiles(String listOfTables) throws Exception {
		ResultSet rst = stmt.executeQuery("select replace(initcap(replace(table_name,'_',' ')),' ' ,'')||'.java',table_name from information_schema.tables  where table_name in (" + listOfTables + ")");
		while( rst.next() ) {
			File file = new File("D:\\entities\\" + rst.getString(1) );
			if( !file.exists() ) {
				file.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(file));
			addImportStmts(rst);
			addAnnotation(rst);
			bw.newLine();
			bw.write("}");
			bw.close();
		}
		rst.close();
	}

	/**
	 * Method to add the import statements in the file
	 * @param rst
	 * @throws Exception
	 */
	private static void addImportStmts(ResultSet rst)throws Exception{
		bw.write("package com.glenwood.glaceemr.server.application.models;");
		bw.newLine();
		bw.newLine();
		bw.write("import java.sql.Timestamp;");
		bw.newLine();
		bw.newLine();
		bw.write("import java.sql.Date;");
		bw.newLine();
		bw.newLine();
		bw.write("import jakarta.persistence.Column;");
		bw.newLine();
		bw.write("import jakarta.persistence.Entity;");
		bw.newLine();
		bw.write("import jakarta.persistence.Id;");
		bw.newLine();
		bw.write("import jakarta.persistence.Table;");
		bw.newLine();
		bw.newLine();
		bw.write("import jakarta.persistence.GeneratedValue;");
		bw.newLine();
		bw.write("import jakarta.persistence.GenerationType;");
		bw.newLine();
		bw.write("import jakarta.persistence.SequenceGenerator;");
		bw.newLine();
		bw.newLine();
		bw.write("import com.fasterxml.jackson.databind.annotation.JsonSerialize;");
		bw.newLine();
		bw.write("import com.backend.multimarkethub.utils.JsonTimestampSerializer;");
		bw.newLine();
		bw.newLine();
		bw.write("@Entity");
		bw.newLine();
		bw.write("@Table(name = \"" + rst.getString(2) + "\")");
		bw.newLine();
		bw.write("public class " + rst.getString(1).replace(".java", "") + " {");
		bw.flush();
	}
	/**
	 * Method to add the annotations
	 * @param rst
	 * @throws Exception
	 */
	private static void addAnnotation(ResultSet rst) throws Exception {
		Statement stmttwo = con.createStatement();
		ResultSet columRst = stmttwo.executeQuery("select * from "+rst.getString(2));
		ResultSetMetaData metaData = columRst.getMetaData();
		for( int i = 1 ; i <= metaData.getColumnCount() ; i++ ) {
			bw.newLine();
			bw.newLine();
			if( isPrimaryKey(rst.getString(2)) && metaData.getColumnName(i).equalsIgnoreCase(primaryKey)) {
				bw.write("\t@Id");
				bw.newLine();
			}
			if(isSequence(rst.getString(2)) && metaData.getColumnName(i).equalsIgnoreCase(sequenceColumn)) {
				bw.write("\t@GeneratedValue(strategy = GenerationType.SEQUENCE, generator=\"" + sequenceConstraint + "\")");
				bw.newLine();
				bw.write("\t@SequenceGenerator(name =\"" + sequenceConstraint + "\", sequenceName=\"" + sequenceConstraint + "\", allocationSize=1)");
				bw.newLine();
			}
			if(metaData.getColumnTypeName(i).equalsIgnoreCase("time") || metaData.getColumnTypeName(i).equalsIgnoreCase("timetz") || metaData.getColumnTypeName(i).equalsIgnoreCase("timestamp") || metaData.getColumnTypeName(i).equalsIgnoreCase("timestamptz")) {
				bw.write("\t@JsonSerialize(using = JsonTimestampSerializer.class)");
				bw.newLine();
			}
			bw.write("\t@Column(name=\"" + metaData.getColumnName(i) + "\")");
			bw.newLine();
			addOOType(metaData.getColumnTypeName(i),metaData.getColumnName(i));
			bw.flush();
		}
		stmttwo.close();
		columRst.close();
	}
	
	private static boolean isSequence(String tableName) throws Exception {
	    Statement statement = con.createStatement();
	    ResultSet resultSet = null;

	    try {
	        String query = "SELECT column_name, column_default " +
	                "FROM information_schema.columns " +
	                "WHERE table_name = '" + tableName + "' AND column_default LIKE 'nextval%'";
	        System.out.println(query);
	        resultSet = statement.executeQuery(query);

	        if (resultSet.next()) {
	            sequenceColumn = resultSet.getString("column_name");

	            // Extracting sequenceConstraint from column_default
	            String columnDefault = resultSet.getString("column_default");
	            int startIdx = columnDefault.indexOf("'") + 1;
	            int endIdx = columnDefault.indexOf(":") - 1;
	            sequenceConstraint = columnDefault.substring(startIdx, endIdx);

	            return true;
	        } else {
	            return false;
	        }
	    } finally {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	        statement.close();
	    }
	}

	/*private static boolean isSequence(String tableName) throws Exception {
		Statement sequenceId = con.createStatement();
		String qry = "select distinct adsrc,attname,relname from pg_class inner join pg_attribute on oid=attrelid" +
				" inner join pg_attrdef   on attrelid=adrelid and adnum=attnum where atthasdef='t' and relname='" + tableName + 
				"' and adsrc ilike '%_seq%' order by 1,2";
		System.out.println(qry);
		ResultSet rst = sequenceId.executeQuery(qry);
		while( rst.next() ) {
			String sequence = rst.getString(1);
			sequenceColumn = rst.getString(2);
			sequenceConstraint =  sequence.substring(sequence.indexOf("'") + 1, sequence.indexOf(":") - 1);
			sequenceId.close();
			return true;
		}
		rst.close();
		sequenceId.close();
		return false;
	}*/

	private static boolean isPrimaryKey(String tableName) throws Exception {
		Statement pkey = con.createStatement();
		String qry = "select pg_class.relname, pg_attribute.attname, pg_index.indisprimary " +
				"from pg_class, pg_attribute, pg_index where pg_class.oid = pg_attribute.attrelid and " +
				"pg_class.oid = pg_index.indrelid and pg_index.indkey[0] = pg_attribute.attnum and " +
				"pg_index.indisprimary = 't' and relname='" + tableName + "'";
		System.out.println(qry);
		ResultSet rst = pkey.executeQuery(qry);
		while( rst.next() ) {
			if( rst.getString(3).equalsIgnoreCase("t") ) {
				primaryKey = rst.getString(2);
				pkey.close();
				rst.close();
				return true;
			}
		}
		rst.close();
		pkey.close();
		return false;
	}

	/**
	 * Method to specify the type of the variable based on column type
	 * @param columnTypeName
	 * @param columnName
	 * @throws Exception
	 */
	private static void addOOType(String columnTypeName,String columnName) throws Exception {
		bw.write("\tprivate ");
		if(columnTypeName.equalsIgnoreCase("serial")) {
			bw.write("Integer ");
		} else if(columnTypeName.equalsIgnoreCase("bpchar")) {
			bw.write("String ");
		} else if(columnTypeName.equalsIgnoreCase("float4")) {
			bw.write("Float ");
		} else if(columnTypeName.equalsIgnoreCase("float8")) {
			bw.write("Double ");
		} else if(columnTypeName.equalsIgnoreCase("numeric")) {
			bw.write("Double ");
		} else if(columnTypeName.equalsIgnoreCase("bigserial")) {
			bw.write("Integer ");
		} else if(columnTypeName.equalsIgnoreCase("int8")) {
			bw.write("Integer ");
		} else if(columnTypeName.equalsIgnoreCase("int2")) {
			bw.write("Integer ");
		} else if(columnTypeName.equalsIgnoreCase("int4")) {
			bw.write("Integer ");
		} else if(columnTypeName.equalsIgnoreCase("bool")) {
			bw.write("Boolean ");
		} else if(columnTypeName.equalsIgnoreCase("time") || columnTypeName.equalsIgnoreCase("timetz") || columnTypeName.equalsIgnoreCase("timestamp") || columnTypeName.equalsIgnoreCase("timestamptz")) {
			bw.write("Timestamp ");
		} else if(columnTypeName.equalsIgnoreCase("text")) {
			bw.write("String ");
		} else if(columnTypeName.equalsIgnoreCase("tsvector")) {
			bw.write("String ");
		} else if(columnTypeName.equalsIgnoreCase("varchar")) {
			bw.write("String ");
		}  else if(columnTypeName.equalsIgnoreCase("date")) {
			bw.write("Date ");
		} else if(columnTypeName.equalsIgnoreCase("bit")) {
			bw.write("Boolean ");
		} else if(columnTypeName.equalsIgnoreCase("interval")) {
			bw.write("Duration ");
		} else if(columnTypeName.equalsIgnoreCase("status")) {
			bw.write("Status ");
		} else {
			System.out.println("" + columnTypeName + ":::::::::" + columnName);
			throw new Exception("Datatype not defined in addOOType Method");
		}
		Statement stmtThree = con.createStatement();
		ResultSet newCol = stmtThree.executeQuery("select replace(replace(initcap(replace('xxxxx'||'" + columnName.replace("_", " ")+"','_',' ')),' ' ,''),'Xxxxx','')");
		newCol.next();
		bw.write(newCol.getString(1)+";");
		stmtThree.close();
		newCol.close();
	}
}
