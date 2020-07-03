package com.magnum.handloom.rest;

import com.magnum.handloom.request.AddAddressRequestBean;
import com.magnum.handloom.request.AddEventRequestBean;
import com.magnum.handloom.request.ArticalDetailsRequestBean;
import com.magnum.handloom.request.ArticalListRequestBean;
import com.magnum.handloom.request.EcomProductRequestBean;
import com.magnum.handloom.request.EventListRequestBean;
import com.magnum.handloom.request.LoginRequestBean;
import com.magnum.handloom.request.OrderHistoryRequestBean;
import com.magnum.handloom.request.PlaceOrderRequestBean;
import com.magnum.handloom.request.RegistrationRequestModel;
import com.magnum.handloom.request.UserAddressRequestBean;
import com.magnum.handloom.response.AddAddressResponseBean;
import com.magnum.handloom.response.AddEventResponseBean;
import com.magnum.handloom.response.ArticalListResponseBean;
import com.magnum.handloom.response.Articaletails;
import com.magnum.handloom.response.EcomCategoryResponseBean;
import com.magnum.handloom.response.EcomProductResponseBean;
import com.magnum.handloom.response.EventListResponseBean;
import com.magnum.handloom.response.GetDashboardContentData;
import com.magnum.handloom.response.LoginResponseBean;
import com.magnum.handloom.response.OrderHistoryResponseBean;
import com.magnum.handloom.response.PlaceOrderResponseBean;
import com.magnum.handloom.response.RegistrationResponseModel;
import com.magnum.handloom.response.SliderImage;
import com.magnum.handloom.response.SlieMenuCategory;
import com.magnum.handloom.response.UserAddressResponseBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Owner on 04-Apr-17.
 */
public interface ApiInterface {

    @POST("index.php/Json/get_category/")
    Call<SlieMenuCategory> getSlideMenuCategory();

    @POST("index.php/Json/get_slider/")
    Call<SliderImage> getSliderImage();

    @POST("index.php/Json/get_artical_by_artical_id/")
    Call<Articaletails> getArticalDetails(@Body ArticalDetailsRequestBean requestBean);

    @POST("index.php/Json/get_dashboard_content/")
    Call<GetDashboardContentData> getDashboardContentData();

    @POST("index.php/Json/get_artical_by_child_category/")
    Call<ArticalListResponseBean> getArticalListData(@Body ArticalListRequestBean requestBean);

    @POST("index.php/Json/get_event_list/")
    Call<EventListResponseBean> getEventListData(@Body EventListRequestBean requestBean);

    @POST("index.php/Json/add_event/")
    Call<AddEventResponseBean> addEventByUser(@Body AddEventRequestBean requestBean);

    @POST("index.php/Json/get_e_comm_category/")
    Call<EcomCategoryResponseBean> getEcomCategoty();

    @POST("index.php/Json/get_e_comm_product/")
    Call<EcomProductResponseBean> getEcomProduct(@Body EcomProductRequestBean requestBean);

    @POST("index.php/Json/user_e_comm_address_add/")
    Call<AddAddressResponseBean> addUserAddress(@Body AddAddressRequestBean requestBean);

    @POST("index.php/Json/get_user_e_comm_address/")
    Call<UserAddressResponseBean> userAddress(@Body UserAddressRequestBean requestBean);

    @POST("index.php/Json/place_order/")
    Call<PlaceOrderResponseBean> sendPlaceOrder(@Body PlaceOrderRequestBean requestBean);

    @POST("index.php/Json/register_user/")
    Call<RegistrationResponseModel> userRegistration(@Body RegistrationRequestModel requestBean);

    @POST("index.php/Json/login/")
    Call<LoginResponseBean> userLogin(@Body LoginRequestBean requestBean);


    @POST("index.php/Json/get_order_history/")
    Call<OrderHistoryResponseBean> getOrderHistoryList(@Body OrderHistoryRequestBean requestBean);





}

