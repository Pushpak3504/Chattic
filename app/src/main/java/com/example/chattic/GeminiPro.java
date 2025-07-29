package com.example.chattic;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.BlockThreshold;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.GenerationConfig;
import com.google.ai.client.generativeai.type.HarmCategory;
import com.google.ai.client.generativeai.type.SafetySetting;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.checkerframework.checker.units.qual.C;

import java.util.Collections;
import java.util.concurrent.Executor;

public class GeminiPro {

    public void getResponse(String query, ResponseCallback r) {
        GenerativeModelFutures model = getModel();
        Content content = new Content.Builder().addText(query).build();

        Executor executor = Runnable::run;

        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String resultText = result.getText();
                r.onResponse(resultText);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        },executor);

    }

    private GenerativeModelFutures getModel(){
        String apiKey = BuildConfig.apiKey;
        SafetySetting h = new SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.ONLY_HIGH);

        GenerationConfig.Builder configBuilder = new GenerationConfig.Builder();
        configBuilder.temperature = 0.9f;
        configBuilder.topK = 16;
        configBuilder.topP = 0.1f;

        GenerationConfig gc = configBuilder.build();

        GenerativeModel gm = new GenerativeModel("gemini-1.5-flash",apiKey,gc, Collections.singletonList(h));


        return GenerativeModelFutures.from(gm);

    }

}
