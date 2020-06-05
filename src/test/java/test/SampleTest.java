package test;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.*;

@ExtendWith(ApplicationExtension.class)
public class SampleTest {

    private Button button;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    public  void start(Stage stage) {
        button = new Button("click me!");
        button.setId("myButton");
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                button.setText("clicked!");
            }
        });
        stage.setScene(new Scene(new StackPane(button), 100, 100));
        stage.show();
    }

    /**
     * @param robot - Will be injected by the test runner.
     */
    @Test
    public void should_contain_button_with_text(FxRobot robot) {
        verifyThat((Button) button, (Matcher<? super Button>) hasText("click me!"));
        // or (lookup by css id):
        verifyThat("#myButton", hasText("click me!"));
        // or (lookup by css class):
        verifyThat(".button", hasText("click me!"));
    }

    /**
     * @param robot - Will be injected by the test runner.
     */
    @Test
    void when_button_is_clicked_text_changes(FxRobot robot) {
        // when:
        robot.clickOn(".button");

        // then:
        verifyThat(button, (Matcher<? super Button>) hasText("clicked!"));
        // or (lookup by css id):
        verifyThat("#myButton", hasText("clicked!"));
        // or (lookup by css class):
        verifyThat(".button", hasText("clicked!"));
    }
}