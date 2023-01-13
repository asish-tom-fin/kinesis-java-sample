package org.example;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.kinesisvideo.KinesisVideoClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesisvideosignaling.KinesisVideoSignalingClient;
import software.amazon.awssdk.services.kinesisvideo.model.GetSignalingChannelEndpointRequest;
import software.amazon.awssdk.services.kinesisvideo.model.SingleMasterChannelEndpointConfiguration;
import software.amazon.awssdk.services.kinesisvideo.model.ChannelProtocol;
import software.amazon.awssdk.services.kinesisvideo.model.ChannelRole;
import software.amazon.awssdk.services.kinesisvideo.model.GetSignalingChannelEndpointResponse;
import software.amazon.awssdk.services.kinesisvideosignaling.model.GetIceServerConfigRequest;
import software.amazon.awssdk.services.kinesisvideosignaling.model.GetIceServerConfigResponse;

import java.net.URI;
import java.net.URISyntaxException;


public class Main {
    public static void main(String[] args) throws URISyntaxException {
        String profile = "";    // replace your aws profile here
        String channelArn = ""; // replace your channel arn here
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.builder().profileName(profile).build();
        final KinesisVideoClient c  = KinesisVideoClient.builder().region(
                Region.AP_SOUTH_1).credentialsProvider(credentialsProvider).build();
        SingleMasterChannelEndpointConfiguration ec = SingleMasterChannelEndpointConfiguration.builder().role(ChannelRole.MASTER
        ).protocols(ChannelProtocol.WSS,ChannelProtocol.HTTPS).build();
        GetSignalingChannelEndpointRequest r = GetSignalingChannelEndpointRequest.builder().channelARN(
                channelArn).singleMasterChannelEndpointConfiguration(ec).build();
        GetSignalingChannelEndpointResponse resp = c.getSignalingChannelEndpoint(r);
        System.out.println(resp.resourceEndpointList().get(0).resourceEndpoint());
        ec = SingleMasterChannelEndpointConfiguration.builder().role(ChannelRole.VIEWER
        ).protocols(ChannelProtocol.WSS,ChannelProtocol.HTTPS).build();
        r = GetSignalingChannelEndpointRequest.builder().channelARN(
                channelArn).singleMasterChannelEndpointConfiguration(ec).build();
        resp = c.getSignalingChannelEndpoint(r);
        System.out.println(resp.resourceEndpointList());

        final KinesisVideoSignalingClient sc  = KinesisVideoSignalingClient.builder().endpointOverride(new URI(resp.resourceEndpointList().get(0).resourceEndpoint())).region(
                Region.AP_SOUTH_1).credentialsProvider(credentialsProvider).build();

        GetIceServerConfigRequest ir = GetIceServerConfigRequest.builder().channelARN(channelArn).service("TURN").build();
        GetIceServerConfigResponse iresp = sc.getIceServerConfig(ir);
        System.out.println(iresp.iceServerList());
        System.out.println(iresp.hasIceServerList());
        System.out.println(iresp.hasIceServerList());
    }
}





