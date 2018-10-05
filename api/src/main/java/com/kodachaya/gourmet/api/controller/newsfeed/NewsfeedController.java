package com.kodachaya.gourmet.api.controller.newsfeed;

import com.kodachaya.gourmet.api.config.CustomUserDetails;
import com.kodachaya.gourmet.api.controller.mapper.ReviewMapper;
import com.kodachaya.gourmet.api.dto.BaseListModel;
import com.kodachaya.gourmet.api.dto.review.ReviewModel;
import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.exception.NotFoundException;
import com.kodachaya.gourmet.api.exception.UnauthorizedException;
import com.kodachaya.gourmet.api.service.newsfeed.NewsfeedService;
import com.kodachaya.gourmet.api.service.user.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsfeedController {

    @Autowired
    private UserService userService;


    @Autowired
    private NewsfeedService newsfeedService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
    })
    @RequestMapping(value = "/newsfeed", method = RequestMethod.GET)
    public @ResponseBody BaseListModel<ReviewModel> getStamps(Pageable pageable) {

        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity userEntity = userService.get(username).orElseThrow(UnauthorizedException::new);

        Page<ReviewEntity> reviewEntities = newsfeedService.get(userEntity.getId(), pageable);
        if (reviewEntities.isFirst() && !reviewEntities.hasContent()) {
            throw new NotFoundException("There is no reviews.");
        }
        List<ReviewModel> reviews = reviewEntities.stream()
                .map(ReviewMapper::map)
                .collect(Collectors.toList());

        BaseListModel<ReviewModel> response = new BaseListModel<>();
        response.setData(reviews);
        response.setCount(reviewEntities.getNumberOfElements());
        response.setTotalCount((int) reviewEntities.getTotalElements());
        response.setNextPage(reviewEntities.hasNext() ? reviewEntities.nextPageable().getPageNumber() : null);
        return response;
    }
}
