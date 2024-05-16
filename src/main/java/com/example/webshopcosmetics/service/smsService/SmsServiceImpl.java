// package com.example.webshopcosmetics.service.smsService;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import com.twilio.Twilio;
// import com.twilio.type.PhoneNumber;
// import com.twilio.rest.api.v2010.account.Message;

// // import jakarta.mail.Message;

// @Service
// public class SmsServiceImpl implements SmsService {
//     // @Value("${twilio.account_sid}")
//     // private String accountSid;

//     // @Value("${twilio.auth_token}")
//     // private String authToken;

//     // @Value("${twilio.phone_number}")
//     // private String fromPhoneNumber;



//     // @Override
//     // public boolean sendSms(String toPhoneNumber, String messageBody) {
//     //     try {
//     //         Twilio.init(accountSid, authToken);

//     //         Message message = Message.creator(
//     //                 new PhoneNumber(toPhoneNumber),
//     //                 new PhoneNumber(fromPhoneNumber),
//     //                 messageBody).create();

//     //         return true; // Gửi tin nhắn thành công
//     //     } catch (Exception e) {
//     //         e.printStackTrace();
//     //         return false; // Gửi tin nhắn thất bại
//     //     }
//     // }

//     // @Override
//     // public boolean sendVerificationCode(String phoneNumber, String verificationCode) {
//         // try {
//         //     // Code để gửi mã xác nhận đến số điện thoại
//         //     System.out.println("Sending verification code to " + phoneNumber + ": " + verificationCode);
//         //     return true; // Trả về true nếu gửi thành công
//         // } catch (Exception e) {
//         //     e.printStackTrace();
//         //     return false; // Trả về false nếu gửi thất bại
//         // }

//         // try {
//         //     // Initialize Twilio client
//         //     Twilio.init(accountSid, authToken);

//         //     // Compose message body
//         //     String messageBody = "Your verification code is: " + verificationCode;

//         //     // Send message
//         //     Message message = Message.creator(
//         //             new PhoneNumber(phoneNumber),  // Receiver's phone number
//         //             new PhoneNumber(fromPhoneNumber),  // Twilio phone number
//         //             messageBody
//         //     ).create();

//         //     return true; // Gửi tin nhắn thành công
//         // } catch (Exception e) {
//         //     e.printStackTrace();
//         //     return false; // Gửi tin nhắn thất bại
//         // }

//     //     try {
//     //         Twilio.init(accountSid, authToken);

//     //         Message message = Message.creator(
//     //                 new PhoneNumber(phoneNumber),
//     //                 new PhoneNumber(fromPhoneNumber),
//     //                 "Your verification code is: " + verificationCode).create();

//     //         return true; // Gửi tin nhắn thành công
//     //     } catch (Exception e) {
//     //         e.printStackTrace();
//     //         return false; // Gửi tin nhắn thất bại
//     //     }
//     // }

//     @Override
//     public boolean sendVerificationCode(String toPhoneNumber, String verificationCode) {
//         try {
//             Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

//             // Chuyển đổi số điện thoại sang định dạng quốc tế E.164
//             String formattedPhoneNumber = toPhoneNumber.startsWith("0") ? "+84" + toPhoneNumber.substring(1) : toPhoneNumber;

//             // Tạo tin nhắn với nội dung là mã xác minh và gửi đến số điện thoại được đăng ký
//             Message message = Message.creator(
//                     new PhoneNumber(formattedPhoneNumber),  // Số điện thoại người nhận (số điện thoại đăng ký trong ứng dụng web)
//                     new PhoneNumber(FROM_PHONE_NUMBER),  // Số điện thoại nguồn (số điện thoại Twilio của bạn)
//                     "Your verification code is: " + verificationCode  // Nội dung tin nhắn
//             ).create();

//             return true; // Gửi tin nhắn thành công
//         } catch (Exception e) {
//             e.printStackTrace();
//             return false; // Gửi tin nhắn thất bại
//         }
//     }
// }
