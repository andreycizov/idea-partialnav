package com.andreycizov.partialnav;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class PartialNavConfigurable implements Configurable {
    private JPanel panelRoot;
    private JComboBox comboPageUpMult;
    private JComboBox comboPageDownMult;
    private JRadioButton multiNavigationRadioButton;
    private JRadioButton staticNavigationRadioButton;
    private JFormattedTextField fieldPageUpStatic;
    private JFormattedTextField fieldPageDownStatic;
    static String navigationTypePropertyName = "com.andreycizov.partialpagenav.nav.type";
    static String MULT = "mult";
    static String STATIC = "static";

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Partial Navigation";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        fieldPageUpStatic.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        fieldPageDownStatic.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));

        PropertiesComponent props = PropertiesComponent.getInstance();
        float multPageUp = props.getFloat(PartialPageUpAction.multPropertyName, PartialPageUpAction.multPropertyDefault);
        float multPageDown = props.getFloat(PartialPageDownAction.multPropertyName, PartialPageDownAction.multPropertyDefault);
        int staticPageUp = props.getInt(PartialPageUpAction.staticPropertyName, PartialPageUpAction.staticPropertyDefault);
        int staticPageDown = props.getInt(PartialPageDownAction.staticPropertyName, PartialPageDownAction.staticPropertyDefault);
        comboPageUpMult.setSelectedItem(Float.toString(multPageUp));
        comboPageDownMult.setSelectedItem(Float.toString(multPageDown));
        fieldPageUpStatic.setValue(staticPageUp);
        fieldPageDownStatic.setValue(staticPageDown);
        if (props.getValue(navigationTypePropertyName, MULT).equals(MULT)) {
            multiNavigationRadioButton.setSelected(true);
        } else {
            staticNavigationRadioButton.setSelected(true);
        }

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
        String pageUpStatic = fieldPageUpStatic.getText();
        String pageDownStatic = fieldPageDownStatic.getText();

        float fPageUpMult = Float.parseFloat(pageUpMult);
        float fPageDownMult = Float.parseFloat(pageDownMult);
        int fPageUpStatic = Integer.parseInt(pageUpStatic);
        int fPageDownStatic = Integer.parseInt(pageDownStatic);

        PropertiesComponent props = PropertiesComponent.getInstance();
        props.setValue(PartialPageUpAction.multPropertyName, fPageUpMult, PartialPageUpAction.multPropertyDefault);
        props.setValue(PartialPageDownAction.multPropertyName, fPageDownMult, PartialPageDownAction.multPropertyDefault);
        props.setValue(PartialPageUpAction.staticPropertyName, fPageUpStatic, PartialPageUpAction.staticPropertyDefault);
        props.setValue(PartialPageDownAction.staticPropertyName, fPageDownStatic, PartialPageDownAction.staticPropertyDefault);
        props.setValue(navigationTypePropertyName, multiNavigationRadioButton.isSelected() ? MULT : STATIC);
    }

    @Override
    public boolean isModified() {
        PropertiesComponent props = PropertiesComponent.getInstance();
        float propPageUpMult = props.getFloat(PartialPageUpAction.multPropertyName, PartialPageUpAction.multPropertyDefault);
        float propPageDownMult = props.getFloat(PartialPageDownAction.multPropertyName, PartialPageDownAction.multPropertyDefault);
        int propPageUpStatic = props.getInt(PartialPageUpAction.staticPropertyName, PartialPageUpAction.staticPropertyDefault);
        int propPageDownStatic = props.getInt(PartialPageDownAction.staticPropertyName, PartialPageDownAction.staticPropertyDefault);
        String propNavigationType = props.getValue(navigationTypePropertyName, MULT);

        String pageUpMult = (String) comboPageUpMult.getSelectedItem();
        String pageDownMult = (String) comboPageDownMult.getSelectedItem();
        String pageUpStatic = fieldPageUpStatic.getText();
        String pageDownStatic = fieldPageDownStatic.getText();
        if (pageUpMult == null || pageDownMult == null || pageUpStatic == null || pageDownStatic == null) {
            return true;
        }
        float fPageUpMult = Float.parseFloat(pageUpMult);
        float fPageDownMult = Float.parseFloat(pageDownMult);
        float fPageUpStatic = Integer.parseInt(pageUpStatic);
        float fPageDownStatic = Integer.parseInt(pageDownStatic);
        String navigationType = multiNavigationRadioButton.isSelected() ? MULT : STATIC;

        return propPageUpMult != fPageUpMult || propPageDownMult != fPageDownMult
                || propPageUpStatic != fPageUpStatic || propPageDownStatic != fPageDownStatic
                || !propNavigationType.equals(navigationType);
    }

    @Override
    public void reset() {
        PropertiesComponent props = PropertiesComponent.getInstance();
        float propPageUpMult = props.getFloat(PartialPageUpAction.multPropertyName, PartialPageUpAction.multPropertyDefault);
        float propPageDownMult = props.getFloat(PartialPageDownAction.multPropertyName, PartialPageDownAction.multPropertyDefault);
        int propPageUpStatic = props.getInt(PartialPageUpAction.staticPropertyName, PartialPageUpAction.staticPropertyDefault);
        int propPageDownStatic = props.getInt(PartialPageDownAction.staticPropertyName, PartialPageDownAction.staticPropertyDefault);
        String propNavigationType = props.getValue(navigationTypePropertyName, MULT);

        comboPageUpMult.setSelectedItem(Float.toString(propPageUpMult));
        comboPageDownMult.setSelectedItem(Float.toString(propPageDownMult));
        fieldPageUpStatic.setValue(propPageUpStatic);
        fieldPageDownStatic.setValue(propPageDownStatic);
        if (propNavigationType.equals(MULT)) {
            multiNavigationRadioButton.setSelected(true);
        } else {
            staticNavigationRadioButton.setSelected(true);
        }
    }
}
