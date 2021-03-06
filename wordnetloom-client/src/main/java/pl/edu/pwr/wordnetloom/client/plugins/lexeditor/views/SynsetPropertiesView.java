package pl.edu.pwr.wordnetloom.client.plugins.lexeditor.views;

import java.awt.Color;
import pl.edu.pwr.wordnetloom.client.plugins.viwordnet.views.ViwnGraphViewUI;
import pl.edu.pwr.wordnetloom.client.systems.listeners.SimpleListenerInterface;
import pl.edu.pwr.wordnetloom.client.workbench.abstracts.AbstractView;
import pl.edu.pwr.wordnetloom.client.workbench.interfaces.Workbench;
import pl.edu.pwr.wordnetloom.synset.model.Synset;

/**
 * wyswietlenie wlasciwosci synsetu
 *
 * @author Max
 */
public class SynsetPropertiesView extends AbstractView implements SimpleListenerInterface {

    private static final Color colorOfSecond = new Color(220, 220, 255);

    /**
     * kontruktor dla klasy
     *
     * @param workbench - wskaznik dla workbencha
     * @param title - etykieta dla okienka
     * @param viewNumber - number widoku
     * @param graphUI
     */
    public SynsetPropertiesView(Workbench workbench, String title, int viewNumber, ViwnGraphViewUI graphUI) {
        super(workbench, title, new SynsetPropertiesViewUI(graphUI));
        if (viewNumber == 2) {
            getUI().setBackgroundColor(colorOfSecond); // kolor dla drugiego
        }
    }

    /**
     * przyszedl komunikat o tym, ze zmieniony zostal zaznaczony synset
     *
     * @param object
     * @param tag
     */
    @Override
    public void doAction(Object object, int tag) {
        SynsetPropertiesViewUI view = (SynsetPropertiesViewUI) getUI();
        view.refreshData((Synset) object);
    }

    /**
     * dodanie obiektu nasługującego zmian w opisie jednoski
     *
     * @param newListener - sluchacz
     */
    public void addChangeListener(SimpleListenerInterface newListener) {
        getUI().addActionListener(newListener);
    }
}
