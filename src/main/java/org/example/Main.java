package org.example;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.kinesisvideo.KinesisVideoClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesisvideosignaling.KinesisVideoSignalingClient;
import software.amazon.awssdk.services.kinesisvideo.model.GetSignalingChannelEndpointRequest;
import software.amazon.awssdk.services.kinesisvideo.model.GetSignalingChannelEndpointResponse;
import software.amazon.awssdk.services.kinesisvideosignaling.model.GetIceServerConfigRequest;
import software.amazon.awssdk.services.kinesisvideosignaling.model.GetIceServerConfigResponse;


public class Main {
    public static void main(String[] args) {
        String profile = "";    // replace your aws profile here
        String channelArn = ""; // replace your channel arn here
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.builder().profileName(profile).build();
        final KinesisVideoClient c  = KinesisVideoClient.builder().region(
                Region.AP_SOUTH_1).credentialsProvider(credentialsProvider).build();
        GetSignalingChannelEndpointRequest r = GetSignalingChannelEndpointRequest.builder().channelARN(channelArn).build();
        GetSignalingChannelEndpointResponse resp = c.getSignalingChannelEndpoint(r);
        System.out.println(resp);

        final KinesisVideoSignalingClient sc  = KinesisVideoSignalingClient.builder().region(
                Region.AP_SOUTH_1).credentialsProvider(credentialsProvider).build();
        GetIceServerConfigRequest ir = GetIceServerConfigRequest.builder().channelARN(channelArn).build();
        GetIceServerConfigResponse iresp = sc.getIceServerConfig(ir);
        System.out.println(iresp);

    }
}





