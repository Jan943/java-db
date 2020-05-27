package pl.sopata.jdbccar;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

@Route
public class CarGui extends VerticalLayout {

    private TextField textFieldId;
    private TextField textFieldMark;
    private TextField textFieldModel;
    private TextField textFieldColor;
    private Button button;
    private CarDao carDao;

    @Autowired
    public CarGui(CarDao carDao) {
        this.textFieldId = new TextField("Id:");
        this.textFieldMark = new TextField("Mark:");
        this.textFieldModel = new TextField("Model:");
        this.textFieldColor = new TextField("Color:");
        this.button = new Button("Dodaj");
        this.carDao = carDao;

        //to validation
        button.addClickListener(clickEvent -> {
            Car car = new Car(Long.parseLong(textFieldId.getValue()), textFieldMark.getValue(), textFieldModel.getValue(), textFieldColor.getValue());
            carDao.save(car);
        });

        add(textFieldId, textFieldMark, textFieldModel, textFieldColor, button);
    }

}
