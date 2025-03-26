package com.project.microservices.adminservice.service;

import com.project.microservices.adminservice.model.ShowReqBody;
import com.project.microservices.adminservice.model.ShowSeatsResponse;
import com.project.microservices.adminservice.show.entity.ShowEntity;

public interface AdminService {

	public ShowEntity addShow(ShowReqBody showInfo);
	public ShowSeatsResponse addShowSeatDetails(ShowReqBody showInfo);
	public ShowSeatsResponse fetchShowSeatDetailsFromSearchService(Integer showId);

}
