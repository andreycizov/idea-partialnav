package com.andreycizov.partialnav;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.options.Configurable;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.Spacer;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.Vector;

public class PartialNavConfigurable implements Configurable {
    private JPanel panelRoot;
    private JComboBox<String> comboPageUpMult;
    private JComboBox<String> comboPageDownMult;
    private JRadioButton multiNavigationRadioButton;
    private JRadioButton staticNavigationRadioButton;
    private JFormattedTextField fieldPageUpStatic;
    private JFormattedTextField fieldPageDownStatic;
    static String navigationTypePropertyName = "com.andreycizov.partialpagenav.nav.type";
    static String MULT = "mult";
    static String STATIC = "static";

    private static final Logger LOGGER = Logger.getInstance(PartialNavConfigurable.class);

    PartialNavConfigurable() {
        panelRoot = new JPanel();
        multiNavigationRadioButton = new JRadioButton("Percent navigation");
        staticNavigationRadioButton = new JRadioButton("Static navigation");

        ButtonGroup group = new ButtonGroup();
        group.add(multiNavigationRadioButton);
        group.add(staticNavigationRadioButton);

        Vector<String> multipliers = new Vector<String>();
        multipliers.add("0.1");
        multipliers.add("0.15");
        multipliers.add("0.25");
        multipliers.add("0.5");
        multipliers.add("0.75");
        multipliers.add("1");
        multipliers.add("1.25");
        multipliers.add("1.5");

        comboPageUpMult = new JComboBox<String>(multipliers);
        comboPageDownMult = new JComboBox<String>(multipliers);

        fieldPageUpStatic = new JFormattedTextField(NumberFormat.getIntegerInstance());
        fieldPageDownStatic = new JFormattedTextField(NumberFormat.getIntegerInstance());

        GridLayoutManager layoutManager = new GridLayoutManager(3, 5, new Insets(10, 10, 10, 10), -1, -1);

        panelRoot.setLayout(layoutManager);

        panelRoot.add(
                new Spacer(), new GridConstraints(2, 0, 1, 5,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_CAN_SHRINK,
                        new Dimension(-1, -1),
                        new Dimension(-1, -1),
                        new Dimension(-1, -1)
                )
        );

        panelRoot.add(multiNavigationRadioButton, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_CAN_SHRINK,
                GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(-1, -1),
                new Dimension(-1, -1),
                new Dimension(-1, -1)
        ));
        panelRoot.add(new JLabel("Page Up (page)"), new GridConstraints(0, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(-1, -1),
                new Dimension(-1, -1),
                new Dimension(-1, -1)
        ));
        panelRoot.add(comboPageUpMult, new GridConstraints(0, 2, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(-1, -1),
                new Dimension(-1, -1),
                new Dimension(-1, -1)
        ));
        panelRoot.add(new JLabel("Page Down (page)"), new GridConstraints(0, 3, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(-1, -1),
                new Dimension(-1, -1),
                new Dimension(-1, -1)
        ));
        panelRoot.add(comboPageDownMult, new GridConstraints(0, 4, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(-1, -1),
                new Dimension(-1, -1),
                new Dimension(-1, -1)
        ));

        panelRoot.add(staticNavigationRadioButton, new GridConstraints(1, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_CAN_SHRINK,
                GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(-1, -1),
                new Dimension(-1, -1),
                new Dimension(-1, -1)
        ));
        panelRoot.add(new JLabel("Page Up (lines)"), new GridConstraints(1, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(-1, -1),
                new Dimension(-1, -1),
                new Dimension(-1, -1)
        ));
        panelRoot.add(fieldPageUpStatic, new GridConstraints(1, 2, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(-1, -1),
                new Dimension(150, -1),
                new Dimension(-1, -1)
        ));
        panelRoot.add(new JLabel("Page Down (lines)"), new GridConstraints(1, 3, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(-1, -1),
                new Dimension(-1, -1),
                new Dimension(-1, -1)
        ));
        panelRoot.add(fieldPageDownStatic, new GridConstraints(1, 4, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(-1, -1),
                new Dimension(150, -1),
                new Dimension(-1, -1)
        ));
    }

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

        if (comboPageDownMult == null || comboPageUpMult == null) {
            return true;
        }

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
