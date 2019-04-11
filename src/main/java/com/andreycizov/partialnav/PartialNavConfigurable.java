package com.andreycizov.partialnav;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PartialNavConfigurable implements Configurable {
    private JPanel panelRoot;
    private JComboBox comboPageUpMult;
    private JComboBox comboPageDownMult;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Partial Navigation";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        float a = PropertiesComponent.getInstance().getFloat(PartialPageUpAction.propertyName, PartialPageUpAction.propertyDefault);
        float b = PropertiesComponent.getInstance().getFloat(PartialPageDownAction.propertyName, PartialPageDownAction.propertyDefault);
        comboPageUpMult.setSelectedItem(Float.toString(a));
        comboPageDownMult.setSelectedItem(Float.toString(b));

        return panelRoot;
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "com.andreycizov.partianav";
    }

    @Override
    public void apply() {
        String pageUpMult = (String) comboPageUpMult.getSelectedItem();
        String pageDownMult = (String) comboPageDownMult.getSelectedItem();

        float fPageUpMult = Float.parseFloat(pageUpMult);
        float fPageDownMult = Float.parseFloat(pageDownMult);

        PropertiesComponent.getInstance().setValue(PartialPageUpAction.propertyName, fPageUpMult, PartialPageUpAction.propertyDefault);
        PropertiesComponent.getInstance().setValue(PartialPageDownAction.propertyName, fPageDownMult, PartialPageDownAction.propertyDefault);
    }

    @Override
    public boolean isModified() {
        float a = PropertiesComponent.getInstance().getFloat(PartialPageUpAction.propertyName, PartialPageUpAction.propertyDefault);
        float b = PropertiesComponent.getInstance().getFloat(PartialPageDownAction.propertyName, PartialPageDownAction.propertyDefault);

        String pageUpMult = (String) comboPageUpMult.getSelectedItem();
        String pageDownMult = (String) comboPageDownMult.getSelectedItem();
        if (pageUpMult == null || pageDownMult == null) {
            return true;
        }
        float fPageUpMult = Float.parseFloat(pageUpMult);
        float fPageDownMult = Float.parseFloat(pageDownMult);

        return a != fPageUpMult || b != fPageDownMult;
    }

    @Override
    public void reset() {
        float a = PropertiesComponent.getInstance().getFloat(PartialPageUpAction.propertyName, PartialPageUpAction.propertyDefault);
        float b = PropertiesComponent.getInstance().getFloat(PartialPageDownAction.propertyName, PartialPageDownAction.propertyDefault);
        comboPageUpMult.setSelectedItem(Float.toString(a));
        comboPageDownMult.setSelectedItem(Float.toString(b));
    }
}
