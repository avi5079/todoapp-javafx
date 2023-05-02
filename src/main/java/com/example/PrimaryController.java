package com.example;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.ArrayList;

import com.example.datamodel.TodoData;
import com.example.datamodel.TodoItem;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

// import java.io.IOException;
// import javafx.fxml.FXML;

public class PrimaryController {

        // @FXML
        // private void switchToSecondary() throws IOException {
        // App.setRoot("secondary");
        // }

        private List<TodoItem> todoItems;

        @FXML
        private ListView<TodoItem> todoListView;

        @FXML
        private TextArea itemDetailsTextArea;

        @FXML
        private Label deadlineLabel;

        public void initialize() {
                TodoItem item1 = new TodoItem("Mail Birthday card", "Buy a 30th birthday card for John",
                                LocalDate.of(2016, Month.APRIL, 25));
                TodoItem item2 = new TodoItem("Doctor's Appointment",
                                "See Dr. Smith at 123 Main Street Bring Paperwork",
                                LocalDate.of(2016, Month.MAY, 25));
                TodoItem item3 = new TodoItem("Finish Desing proposal for client",
                                "I promised Mike I'd email website mockups",
                                LocalDate.of(2016, Month.APRIL, 22));
                TodoItem item4 = new TodoItem("Pickup Doug a the train station", "Doug's arriving on March 12",
                                LocalDate.of(2016, Month.MARCH, 12));
                TodoItem item5 = new TodoItem("Pick up dry cleaning", "The Clothes should be ready by Wednesday",
                                LocalDate.of(2016, Month.APRIL, 20));
                todoItems = new ArrayList<>();
                todoItems.add(item1);
                todoItems.add(item2);
                todoItems.add(item3);
                todoItems.add(item4);
                todoItems.add(item5);

                TodoData.getInstance().setTodoItems(todoItems);

                todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
                        @Override
                        public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue,
                                        TodoItem newValue) {
                                if (newValue != null) {
                                        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                                        itemDetailsTextArea.setText(item.getDetails());
                                        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                                        deadlineLabel.setText(df.format(item.getDeadline()));
                                }
                        }
                });

                todoListView.getItems().setAll(todoItems);
                todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                todoListView.getSelectionModel().selectFirst();

        }

        @FXML
        public void handleClickListView() {
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                // System.out.println(item);
                // StringBuilder sb = new StringBuilder(item.getDetails());
                // sb.append("\n\n\n\n");
                // sb.append("Due: ");
                // sb.append(item.getDeadline());
                // itemDetailsTextArea.setText(sb.toString());

                itemDetailsTextArea.setText(item.getDetails());
                deadlineLabel.setText(item.getDeadline().toString());

        }

}
