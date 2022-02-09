package com.joslin.youtubedataapi.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.google.common.base.Joiner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class VideoService {

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final String REFRESH_TOKEN = "1//04V5g5w0x-VeYCgYIARAAGAQSNwF-L9IrHdLTzUzzq5xLFA3SJtEn3gFpz2hQ1bI-MeOHa_RAUScVOZEy1Avkp-NIWOfhCY2ZbIQ";

    private static YouTube youtube;

    private  static final  String ACCESS_TOKEN= "ya29.A0ARrdaM9__p7JeMnb3qSR62ARHhnPRnjShKYdKAo9YDbjgGAoUTAnzWBB3rp9nTC8lq9WTt8vScJhkJbERLkMI1Ugjh1O42PWHtMdRT50q4pVe4ni1KnWUggtdmPrFJ1QH1ixFgg2CWgmmEqcdjUVmXdvuGEF";


    private Credential generateCredentialWithUserApprovedToken() throws IOException,
            GeneralSecurityException {

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, VideoService.class.getResourceAsStream("/api_key/client_secret.json"));
        return new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT).setJsonFactory(JSON_FACTORY)
                .setClientSecrets(clientSecrets).build().setRefreshToken(REFRESH_TOKEN);
    }

    public List<Video> findVideosByPlayListId(String playListId) {

        List<String> videoIds = new ArrayList<>();
        try {

            Credential credential = generateCredentialWithUserApprovedToken();

            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("joslin-youtube-api").build();


            YouTube.Channels.List channelRequest = youtube.channels().list("contentDetails");
            channelRequest.setMine(true);
            channelRequest.setFields("items/contentDetails,nextPageToken,pageInfo");
            ChannelListResponse channelResult = channelRequest.execute();

            List<Channel> channelsList = channelResult.getItems();

            if (channelsList != null) {

                List<PlaylistItem> playlistItemList = new ArrayList<>();

                YouTube.PlaylistItems.List playlistItemRequest =
                        youtube.playlistItems().list("id,contentDetails");
                playlistItemRequest.setPlaylistId(playListId);


                playlistItemRequest.setFields(
                        "items(contentDetails/videoId),nextPageToken,pageInfo");


                String nextToken = "";

                do {
                    playlistItemRequest.setPageToken(nextToken);
                    PlaylistItemListResponse playlistItemResult = playlistItemRequest.execute();

                    playlistItemList.addAll(playlistItemResult.getItems());

                    nextToken = playlistItemResult.getNextPageToken();
                } while (nextToken != null);

              for (int i=0; i < playlistItemList.size(); i++){

                 String id =  playlistItemList.get(i).getContentDetails().getVideoId();
                 videoIds.add(id);
              }
            }

        }catch (GoogleJsonResponseException e) {
            e.printStackTrace();
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        return findVideosById(videoIds);
    }



    public  List<Video>findVideosById(List<String> videoIds){

        List<Video>videoList = new ArrayList<>();

        try {
           Credential credential = generateCredentialWithUserApprovedToken();

           youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("joslin-youtube-api").build();

           YouTube.Search.List search = youtube.search().list("id");

           search.setType("video");

           SearchListResponse searchResponse = search.execute();
           List<SearchResult> searchResultList = searchResponse.getItems();
           searchResponse.getNextPageToken();
            if (searchResultList != null) {

                Joiner stringJoiner = Joiner.on(',');
                String videoId = stringJoiner.join(videoIds);

                YouTube.Videos.List listVideosRequest = youtube.videos().list("items","snippet,player").setId(videoId)
                        .setFields("items(id,player,snippet/description,snippet/title,snippet/thumbnails)");

                VideoListResponse listResponse = listVideosRequest.execute();

                videoList = listResponse.getItems();

                //System.out.println("TESTE: " + videoList.size());
                //prettyPrint(videoList.size(), videoList.iterator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return videoList;
    }

}
