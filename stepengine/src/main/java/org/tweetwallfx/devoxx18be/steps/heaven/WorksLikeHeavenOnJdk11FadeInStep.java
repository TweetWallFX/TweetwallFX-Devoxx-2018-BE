/*
 * The MIT License
 *
 * Copyright 2018 TweetWallFX
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tweetwallfx.devoxx18be.steps.heaven;

import java.util.Collection;
import java.util.Collections;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.tweetwallfx.controls.WordleSkin;
import org.tweetwallfx.devoxx18be.steps.AbstractConfig;
import org.tweetwallfx.stepengine.api.DataProvider;
import org.tweetwallfx.stepengine.api.Step;
import org.tweetwallfx.stepengine.api.StepEngine;
import org.tweetwallfx.stepengine.api.config.StepEngineSettings;

/**
 * Step displaying "#WorksLikeHeavenOnJDK11" if running on 11.
 */
public class WorksLikeHeavenOnJdk11FadeInStep implements Step {

    private Config config;

    private WorksLikeHeavenOnJdk11FadeInStep(Config config) {
        this.config = config;
    }    
    
    @Override
    public boolean shouldSkip(StepEngine.MachineContext context) {
        return !System.getProperty("java.version").equals("11");
    }

    @Override
    public void doStep(StepEngine.MachineContext context) {
        WordleSkin wordleSkin = context.get("WordleSkin", WordleSkin.class);
        Label hashTag = new Label("#WorksLikeHeavenOnJDK11");
        hashTag.setLayoutX(config.layoutX);
        hashTag.setLayoutY(config.layoutY);
        hashTag.setStyle("-fx-font-size: 30px; -fx-text-fill: #f68b1f; -fx-font-weight: bold;");
        hashTag.setOpacity(0);
        hashTag.setId("hashTagLabel");
        FadeTransition ftHashTag = new FadeTransition(Duration.seconds(3), hashTag);
        ftHashTag.setToValue(1);
        ftHashTag.setOnFinished(e -> context.proceed());
        wordleSkin.getPane().getChildren().add(hashTag);
        ftHashTag.play();
    }

    /**
     * Implementation of {@link Step.Factory} as Service implementation creating
     * {@link WorksLikeHeavenOnJdk11FadeInStep}.
     */
    public static final class FactoryImpl implements Step.Factory {

        @Override
        public WorksLikeHeavenOnJdk11FadeInStep create(final StepEngineSettings.StepDefinition stepDefinition) {
            return new WorksLikeHeavenOnJdk11FadeInStep(stepDefinition.getConfig(Config.class));
        }

        @Override
        public Class<WorksLikeHeavenOnJdk11FadeInStep> getStepClass() {
            return WorksLikeHeavenOnJdk11FadeInStep.class;
        }

        @Override
        public Collection<Class<? extends DataProvider>> getRequiredDataProviders(final StepEngineSettings.StepDefinition stepSettings) {
            return Collections.emptyList();
        }
    }
    
    public static class Config extends AbstractConfig {

        public double layoutX = 0;
        public double layoutY = 0;

    }      
}
