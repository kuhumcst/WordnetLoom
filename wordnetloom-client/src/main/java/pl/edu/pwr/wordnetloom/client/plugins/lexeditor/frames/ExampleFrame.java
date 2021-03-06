package pl.edu.pwr.wordnetloom.client.plugins.lexeditor.frames;

import com.alee.laf.rootpane.WebFrame;
import pl.edu.pwr.wordnetloom.client.plugins.lexeditor.panel.ExamplePanel;
import pl.edu.pwr.wordnetloom.client.systems.ui.DialogWindow;
import pl.edu.pwr.wordnetloom.client.utils.Labels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExampleFrame extends DialogWindow implements ActionListener {

    private static final long serialVersionUID = -8880344925810883670L;
    private final ExamplePanel panel;
    private String example;
    private String toReturn;

    public ExampleFrame(WebFrame baseFrame, String title, String example) {
        super(baseFrame, title, 450, 200);
        this.example = example;
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(baseFrame);
        panel = new ExamplePanel();
        panel.getBtnSave().addActionListener(this);
        panel.getBtnCancel().addActionListener(this);
        add(panel, "hfill");
        pack();
    }

    public void setExample(String example) {
        panel.getExampleTextArea().setText(example);
    }

    public String getExample() {
        return toReturn;
    }

    public void changeBtnName() {
        panel.getBtnSave().setText(Labels.CHANGE);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == panel.getBtnSave()) {
            toReturn = panel.getExampleTextArea().getText();
            setVisible(false);
            dispose();
        } else if (event.getSource() == panel.getBtnCancel()) {
            toReturn = example;
            setVisible(false);
            dispose();
        }
    }

    static public String showModal(WebFrame frame, String title, String example, boolean editable) {
        ExampleFrame modalFrame = new ExampleFrame(frame, title, example);
        if (editable) {
            modalFrame.changeBtnName();
        }
        if (example != null && !"".equals(example)) {
            modalFrame.setExample(example);
        }
        modalFrame.setVisible(true);
        return modalFrame.getExample();
    }
}
