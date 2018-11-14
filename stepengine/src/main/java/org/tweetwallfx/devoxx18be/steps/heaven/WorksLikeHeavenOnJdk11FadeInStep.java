/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tweetwallfx.devoxx18be.steps.heaven;

import java.util.Collection;
import java.util.Collections;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.tweetwallfx.controls.WordleSkin;
import org.tweetwallfx.devoxx18be.steps.AbstractConfig;
import org.tweetwallfx.devoxx18be.steps.Devoxx18ShowTopRatedWeek;
import org.tweetwallfx.stepengine.api.*;
import org.tweetwallfx.stepengine.api.config.StepEngineSettings;

/**
 *
 * @author sven
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
     * {@link Devoxx18ShowTopRatedWeek}.
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
