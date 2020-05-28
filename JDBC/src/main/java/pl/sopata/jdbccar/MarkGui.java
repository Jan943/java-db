package pl.sopata.jdbccar;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Route
public class MarkGui extends VerticalLayout {

    private TextField textFieldMark;
    private TextArea textArea;
    private Button button;
    private CarDao carDao;

    @Autowired
    public MarkGui(CarDao carDao) {
        this.textFieldMark = new TextField("Mark:");
        this.textArea = new TextArea();
        this.button= new Button("Pokaż");

        //to validation
        button.addClickListener(x -> {
            List<Map<String, Object>> maps = carDao.showByMark(textFieldMark.getValue());
            textArea.setValue(maps.toString());
        });

        this.carDao = carDao;

        add(textFieldMark,textArea);
    }
}
