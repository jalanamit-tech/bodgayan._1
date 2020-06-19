package com.winbee.bodhayanacademy.WebApi;

import com.winbee.bodhayanacademy.Model.AfterPaymentModel;
import com.winbee.bodhayanacademy.Model.AssignmentToSubmit;
import com.winbee.bodhayanacademy.Model.BannerModel;
import com.winbee.bodhayanacademy.Model.BranchName;
import com.winbee.bodhayanacademy.Model.CoursePay;
import com.winbee.bodhayanacademy.Model.DemoClas;
import com.winbee.bodhayanacademy.Model.DummyModel;
import com.winbee.bodhayanacademy.Model.ForgetMobile;
import com.winbee.bodhayanacademy.Model.LiveClass;
import com.winbee.bodhayanacademy.Model.NotificationModel;
import com.winbee.bodhayanacademy.Model.OtpVerify;
import com.winbee.bodhayanacademy.Model.PaymentModel;
import com.winbee.bodhayanacademy.Model.PurchasedMainModel;
import com.winbee.bodhayanacademy.Model.RefCode;
import com.winbee.bodhayanacademy.Model.RefUser;
import com.winbee.bodhayanacademy.Model.ResetPassword;
import com.winbee.bodhayanacademy.Model.ResultModel;
import com.winbee.bodhayanacademy.Model.SIACDetailsMainModel;
import com.winbee.bodhayanacademy.Model.SIADMainModel;
import com.winbee.bodhayanacademy.Model.SectionDetailsMainModel;
import com.winbee.bodhayanacademy.Model.SemesterName;
import com.winbee.bodhayanacademy.Model.StartTestModel;
import com.winbee.bodhayanacademy.Model.SubmitAssignment;
import com.winbee.bodhayanacademy.Model.SubmittedAssignment;
import com.winbee.bodhayanacademy.Model.UrlName;
import com.winbee.bodhayanacademy.Model.UrlNewQuestion;
import com.winbee.bodhayanacademy.Model.UrlQuestion;
import com.winbee.bodhayanacademy.Model.UrlQuestionSolution;
import com.winbee.bodhayanacademy.Model.UrlSolution;
import com.winbee.bodhayanacademy.Model.ViewResult;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ClientApi {

    @POST("fetch_user_cover_information.php")
    Call<RefCode> refCodeSignIn(
            @Query("SubURL") int SubURL,
            @Query("username") String username,
            @Query("password") String password,
            @Query("refcode") String refcode
    );

    @POST("user_registration_information.php")
    Call<RefUser> refUserSignIn(
            @Query("SubURL") int SubURL,
            @Query("name") String name,
            @Query("email") String email,
            @Query("mobile") String mobile,
            @Query("refcode") String refcode,
            @Query("password") String password);

    @POST("fetch_bucket_cover_information.php")
    Call<ArrayList<BranchName>> getBranchId(
            @Query("SubURL") int SubURL,
            @Query("ORG_ID") String ORG_ID,
            @Query("PARENT_ID") String PARENT_ID,
            @Query("USER_ID") String USER_ID
    );

    @POST("fetch_bucket_cover_information.php")
    Call<ArrayList<SemesterName>> getSemester(
            @Query("SubURL") int SubURL,
            @Query("ORG_ID") String ORG_ID,
            @Query("PARENT_ID") String PARENT_ID,
            @Query("USER_ID") String USER_ID
    );


    @POST("fetch_bucket_cover_information_puchase.php")
    Call<CoursePay> getSemesterName(
            @Query("SubURL") int SubURL,
            @Query("ORG_ID") String ORG_ID,
            @Query("PARENT_ID") String PARENT_ID
    );


    @POST("fetch_bucket_cover_information.php")
    Call<ArrayList<UrlName>> getTopic(
                    @Query("SubURL") int SubURL,
                    @Query("ORG_ID") String ORG_ID,
                    @Query("PARENT_ID") String PARENT_ID,
                    @Query("USER_ID") String USER_ID
            );

    @POST("fetch_bucket_cover_information.php")
    Call<ArrayList<DemoClas>> getDemoTopic(
            @Query("SubURL") int SubURL,
            @Query("ORG_ID") String ORG_ID,
            @Query("PARENT_ID") String PARENT_ID,
            @Query("USER_ID") String USER_ID
    );

    @POST("send-otp.php")
    Call<ForgetMobile> getForgetMobile(
            @Query("SubURL") int SubURL,
            @Query("username") String username
    );


    @POST("reset-password.php")
    Call<ResetPassword> getResetPassword(
            @Query("SubURL") int SubURL,
            @Query("username") String username,
            @Query("otp") String otp,
            @Query("new_password") String new_password
    );

    @POST("doubt-session.php")
    Call<ArrayList<UrlQuestion>> getQuestion(
            @Query("DocumentId") String DocumentId
    );


    @POST("doubt-session.php")
    Call<ArrayList<UrlSolution>> getSolution(
            @Query("DocumentId") String DocumentId,
            @Query("filename") String filename
    );

    @POST("fetch_live_classes.php")
    Call<ArrayList<LiveClass>> getLive(
            @Query("SubURL") int SubURL,
            @Query("ORG_ID") String ORG_ID,
            @Query("USER_ID") String USER_ID
    );

    @POST("verify-otp.php")
    Call<OtpVerify> getOtpVerify(
            @Query("SubURL") int SubURL,
            @Query("username") String username,
            @Query("otp") String otp
    );

    @FormUrlEncoded
    @POST("submit-doubt.php")
    Call<UrlQuestionSolution> getQuestionSolution(
            @Field("filename") String filename,
            @Field("answer") String answer,
            @Field("DocumentId") String DocumentId,
            @Field("userid") String userid
    );

    @FormUrlEncoded
    @POST("submit-doubt.php")
    Call<UrlNewQuestion> getNewQuestion(
            @Field("title") String title,
            @Field("question") String question,
            @Field("userid") String userid,
            @Field("DocumentId") String DocumentId
    );

    //online test series

    @POST("fetch-section-details.php")
    @FormUrlEncoded
    Call<SectionDetailsMainModel> fetchSectionDetails(
            @Field("org_code") String org_code,
            @Field("auth_code") String auth_code
    );

    @POST("fetch-section-individual-assessment-cover-details.php")
    @FormUrlEncoded
    Call<SIACDetailsMainModel> fetchSIACDetails(
            @Field("org_code") String org_code,
            @Field("auth_code") String auth_code,
            @Field("bucket_code") String bucket_code
    );

    @POST("fetch-section-individual-assessment-data.php")
    @FormUrlEncoded
    Call<SIADMainModel> fetchSIADDATA(
            @Field("org_code") String org_code,
            @Field("auth_code") String auth_code,
            @Field("bucket_code") String bucket_code,
            @Field("paper_code") String paper_code
    );


    @POST("Start-Exam-Paper.php")
    @FormUrlEncoded
    Call<StartTestModel> getStartTest(
            @Field("OrgID") String OrgID,
            @Field("UserID") String UserID,
            @Field("PaperID") String PaperID,
            @Field("ExamStatus") String ExamStatus,
            @Field("Read_Instruction") String Read_Instruction
    );


    @POST("Submit-Exam-Paper.php")
    @FormUrlEncoded
    Call<ResultModel> submitResponse(
            @Field("CoachingID") String CoachingID,
            @Field("PaperID") String PaperID,
            @Field("UserID") String UserID,
            @Field("Response") JSONArray jsonArray,
            @Field("Staticstics") String Staticstics,
            @Field("Submit_Exam_Paper") boolean Submit_Exam_Paper
    );


    @POST("view-result.php")
    @FormUrlEncoded
    Call<ViewResult> viewResult(
            @Field("OrgID") String OrgID,
            @Field("PaperID") String PaperID,
            @Field("UserID") String UserID
    );

    //payment data

    @POST("get-order-id-api.php")
    @FormUrlEncoded
    Call<PaymentModel> fetchPaymentData(
            @Field("course_id") String course_id,
            @Field("user_id") String user_id,
            @Field("amount_org_id") String amount_org_id,
            @Field("org_id") String org_id
    );



   // @POST("insert_purchased_bucket_information.php")
    @POST("dummy-payment.php")
    Call<DummyModel> getPaymentDetail(
            @Query("gt_order_id") String gt_order_id,
            @Query("gt_payment_id") String gt_payment_id
    );

    //purchased course

    @POST("fetch_purchased_bucket_information.php")
    Call<PurchasedMainModel> getCourseById(
            @Query("SubURL") int SubURL,
            @Query("USER_ID") String USER_ID,
            @Query("ORG_ID") String ORG_ID
    );

    @POST("fetch_bucket_cover_information.php")
    Call<ArrayList<SemesterName>> getCourseSubject(
            @Query("SubURL") int SubURL,
            @Query("ORG_ID") String ORG_ID,
            @Query("PARENT_ID") String PARENT_ID,
            @Query("USER_ID") String USER_ID
    );


//    // for assignment
//
//    @POST("fetch_assignment_data.php")
//    Call<AssignmentToSubmit> getAllAssignment(
//            @Query("org_id") String org_id,
//            @Query("user_id") String user_id
//    );
//
//
//    @POST("insert_assignment_data.php")
//    @FormUrlEncoded
//    Call<SubmitAssignment> getSubmitAssignment(
//            @Field("org_id") String org_id,
//            @Field("user_id") String user_id,
//            @Field("course_id") String course_id,
//            @Field("assignment_id") String assignment_id,
//            @Field("attachment") String attachment,
//            @Field("description") String description
//    );

    // for assignment

    @POST("fetch_assignment_data.php")
    Call<AssignmentToSubmit> getAllAssignment(
            @Query("org_id") String org_id,
            @Query("user_id") String user_id
    );


    @POST("fetch_assignment_submitted_student.php")
    Call<SubmittedAssignment> getSubmitedAssignment(
            @Query("org_id") String org_id,
            @Query("user_id") String user_id
    );


    @POST("insert_assignment_data.php")
    @FormUrlEncoded
    Call<SubmitAssignment> getSubmitAssignment(
            @Field("org_id") String org_id,
            @Field("user_id") String user_id,
            @Field("course_id") String course_id,
            @Field("assignment_id") String assignment_id,
            @Field("attachment") String attachment,
            @Field("description") String description
    );


    @POST("fetch-notification-data.php")
    Call<ArrayList<NotificationModel>> getNotification(
            @Query("org_id") String org_id,
            @Query("user_id") String user_id
    );

    @POST("fetch-cover-banner.php")
    Call<ArrayList<BannerModel>> getBanner(
            @Query("org_id") String org_id
    );
}
