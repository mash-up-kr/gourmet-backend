package com.kodachaya.gourmet.api.controller.me;

import com.kodachaya.gourmet.api.controller.mapper.ReviewMapper;
import com.kodachaya.gourmet.api.controller.mapper.UserMapper;
import com.kodachaya.gourmet.api.controller.mapper.WishMapper;
import com.kodachaya.gourmet.api.dto.BaseListModel;
import com.kodachaya.gourmet.api.dto.review.ReviewModel;
import com.kodachaya.gourmet.api.dto.user.UserModel;
import com.kodachaya.gourmet.api.dto.wish.WishModel;
import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.entity.wish.WishEntity;
import com.kodachaya.gourmet.api.exception.BadRequestException;
import com.kodachaya.gourmet.api.exception.NotFoundException;
import com.kodachaya.gourmet.api.exception.UnauthorizedException;
import com.kodachaya.gourmet.api.config.CustomUserDetails;
import com.kodachaya.gourmet.api.service.review.ReviewService;
import com.kodachaya.gourmet.api.service.user.UserService;
import com.kodachaya.gourmet.api.service.wish.WishService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import com.kodachaya.gourmet.api.service.storage.StorageService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MeController {


    private final StorageService storageService;

    public MeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private UserService userService;


    @Autowired
    private ReviewService reviewService;


    @Autowired
    private WishService wishService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserModel.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public @ResponseBody UserModel getMe() {
        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity entity = userService.get(username).orElseThrow(UnauthorizedException::new);
        return UserMapper.map(entity, reviewService.getReviewCount(entity.getId()), wishService.getWishCount(entity.getId()));
  }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserModel.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/me", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserModel putMe(@RequestPart(value = "profile", required = false) MultipartFile profile,
                           @RequestPart(value = "introduce", required = false) String introduce) throws FileNotFoundException {

        if (profile == null && (introduce == null || introduce.length() == 0)) {
            throw new BadRequestException("Please input profile or introduce");
        }

        String profileImageUrl = null;
        if (profile != null) {
            // upload profile to storage
            profileImageUrl = storageService.uploadProfile(profile);
        }


        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity entity = userService.get(username).orElseThrow(UnauthorizedException::new);
        entity = userService.updateAdditionalInfo(entity.getId(), profileImageUrl, introduce);
        return UserMapper.map(entity, wishService.getWishCount(entity.getId()), reviewService.getReviewCount(entity.getId()));
    }


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
    @RequestMapping(value = "/me/wishlist", method = RequestMethod.GET)
    public @ResponseBody BaseListModel<WishModel> getWishes(Pageable pageable) {

        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity userEntity = userService.get(username).orElseThrow(UnauthorizedException::new);

        Page<WishEntity> wishEntities = wishService.getWishes(userEntity, pageable);
        if (wishEntities.isFirst() && !wishEntities.hasContent()) {
            throw new NotFoundException("Please wish first");
        }

        BaseListModel<WishModel> response = new BaseListModel<>();
        List<WishModel> wishlist = wishEntities.stream()
                .map(WishMapper::map)
                .collect(Collectors.toList());
        response.setData(wishlist);
        response.setCount(wishEntities.getNumberOfElements());
        response.setTotalCount((int) wishEntities.getTotalElements());
        response.setNextPage(wishEntities.hasNext() ? wishEntities.nextPageable().getPageNumber() : null);
        return response;
    }


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
    @RequestMapping(value = "/me/stamps", method = RequestMethod.GET)
    public @ResponseBody BaseListModel<ReviewModel> getStamps(Pageable pageable) {

        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity userEntity = userService.get(username).orElseThrow(UnauthorizedException::new);

        Page<ReviewEntity> reviewEntities = reviewService.getReviews(userEntity, pageable);
        if (reviewEntities.isFirst() && !reviewEntities.hasContent()) {
            throw new NotFoundException("Please review first");
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
