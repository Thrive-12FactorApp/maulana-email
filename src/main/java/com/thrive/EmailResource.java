package com.thrive;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import com.thrive.model.EmailRequest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/email")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmailResource {

    @ConfigProperty(name = "mail.sender")
    private String sender;

    @ConfigProperty(name = "mail.api.key")
    private String apiKey;

    @ConfigProperty(name = "mail.api.secret")
    private String apiSecretKey;



    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasyMAUL";
    }

    @POST
    public String sendEmail(EmailRequest emailRequest) throws MailjetException {
        this.sendmailjet(emailRequest);
        return "Delivered";
    }

    private void sendmailjet(EmailRequest emailRequest) throws MailjetException {
        MailjetRequest request;
        MailjetResponse response;

        ClientOptions options = ClientOptions.builder()
                .apiKey(apiKey)
                .apiSecretKey(apiSecretKey)
                .build();

        MailjetClient client = new MailjetClient(options);

        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", sender)
                                        .put("Name", "Mailjet Pilot"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", emailRequest.getEmail())
                                                .put("Name", emailRequest.getNama())))
                                .put(Emailv31.Message.SUBJECT, emailRequest.getSubjek())
                                .put(Emailv31.Message.TEXTPART, emailRequest.getBody())));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }

}
