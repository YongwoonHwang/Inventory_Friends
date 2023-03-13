package main;

/*
참조 : https://github.com/aterai/java-swing-tips/blob/master/CheckedComboBox/src/java/example/MainPanel.java
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.plaf.basic.ComboPopup;

public final class CheckableComboBox extends JPanel {
    CheckableItem[] m = {};
    String[] elegment;
    ComboBoxModel model;
    CheckedComboBox checkedComboBox;

    public CheckableComboBox() {
        super(new BorderLayout());
        JPanel p = new JPanel(new GridLayout(0, 1));
        p.add(new CheckedComboBox<>(makeModel()));
        add(p, BorderLayout.CENTER);
    }

    public CheckableComboBox(String str[]) {
        super(new BorderLayout());
        elegment = str;
        for (int i = 0; i < str.length; i++) {
            CheckableItem[] tmpModel = new CheckableItem[m.length + 1];
            System.arraycopy(m, 0, tmpModel, 0, m.length);
            tmpModel[i] = new CheckableItem(str[i], false);
            m = tmpModel.clone();
//            System.out.println(Arrays.toString(m));
        }
        model = new DefaultComboBoxModel<>(m);
        JPanel p = new JPanel(new GridLayout(0, 1));
        checkedComboBox = new CheckedComboBox<>(model);
        p.add(checkedComboBox);
        add(p, BorderLayout.CENTER);
    }

    public String getSelectItems(){
        String items = "";
        for (int i = 0; i < model.getSize(); i++) {
            if(m[i].isSelected()){
                items = items + m[i].toString() + ", ";
            }
        }
        try{
            items = items.substring(0, items.length()-2);
        } catch (Exception e){
            System.out.println(e);
        }
        return items;
    }

    public void Clear(){
        for (int i = 0; i < model.getSize(); i++) {
            if(m[i].isSelected()){
                m[i].setSelected(false);
            }
        }
    }

    public void splitStrAndCheck(String str){
        String[] result = str.split(", ");
        ArrayList<String> result2 = new ArrayList<>();
        String chkStr = "";
        for (int i = 0; i < elegment.length; i++) {
            boolean chk = false;
            for (int j = 0; j < result.length; j++) {
                if(elegment[i].equals(result[j])) {
                    chkStr = result[j];
                    chk = true;
                }
            }
            if(chk){
                result2.add(chkStr);
            }else
                result2.add("");
        }

        CheckableItem[] chkModel = new CheckableItem[elegment.length];
        for (int i = 0; i < elegment.length; i++) {

            if(elegment[i].equals(result2.get(i))){
                chkModel[i] = new CheckableItem(elegment[i], true);
            }else {
                chkModel[i] = new CheckableItem(elegment[i], false);
            }
//            System.arraycopy(m, 0, chkModel, 0, m.length);

            m = chkModel.clone();
        }
        model = new DefaultComboBoxModel<>(m);
        checkedComboBox.setModel(model);
    }

    private ComboBoxModel<CheckableItem> makeModel() {

        return new DefaultComboBoxModel<>(m);
    }


    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.pack();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(new CheckableComboBox());
        f.setVisible(true);
    }
}

class CheckableItem {
    private final String text;
    private boolean selected;

    protected CheckableItem(String text, boolean selected) {
        this.text = text;
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override public String toString() {
        return text;
    }
}

class CheckedComboBox<E extends CheckableItem> extends JComboBox<E> {
    protected boolean keepOpen;
    private final JPanel panel = new JPanel(new BorderLayout());

    protected CheckedComboBox(ComboBoxModel<E> model) {
        super(model);
    }

    @Override public Dimension getPreferredSize() {
        return new Dimension(200, 20);
    }

    @Override public void updateUI() {
        setRenderer(null);
        super.updateUI();

        Accessible a = getAccessibleContext().getAccessibleChild(0);
        if (a instanceof ComboPopup) {
            ((ComboPopup) a).getList().addMouseListener(new MouseAdapter() {
                @Override public void mousePressed(MouseEvent e) {
                    JList<?> list = (JList<?>) e.getComponent();
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        keepOpen = true;
                        updateItem(list.locationToIndex(e.getPoint()));
                    }
                }
            });
        }

        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        JCheckBox check = new JCheckBox();
        check.setOpaque(false);
        setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            panel.removeAll();
            Component c = renderer.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            if (index < 0) {
                String txt = getCheckedItemString(list.getModel());
                JLabel l = (JLabel) c;
                l.setText(txt.isEmpty() ? " " : txt);
                l.setOpaque(false);
                l.setForeground(list.getForeground());
                panel.setOpaque(false);
            } else {
                check.setSelected(value.isSelected());
                panel.add(check, BorderLayout.WEST);
                panel.setOpaque(true);
                panel.setBackground(c.getBackground());
            }
            panel.add(c);
            return panel;
        });
        initActionMap();
    }

    protected void initActionMap() {
        KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0);
        getInputMap(JComponent.WHEN_FOCUSED).put(ks, "checkbox-select");
        getActionMap().put("checkbox-select", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                Accessible a = getAccessibleContext().getAccessibleChild(0);
                if (a instanceof ComboPopup) {
                    updateItem(((ComboPopup) a).getList().getSelectedIndex());
                }
            }
        });
    }

    protected void updateItem(int index) {
        if (isPopupVisible() && index >= 0) {
            E item = getItemAt(index);
            item.setSelected(!item.isSelected());
            setSelectedIndex(-1);
            setSelectedItem(item);
        }
    }

    @Override public void setPopupVisible(boolean v) {
        if (keepOpen) {
            keepOpen = false;
        } else {
            super.setPopupVisible(v);
        }
    }

    protected static <E extends CheckableItem> String getCheckedItemString(ListModel<E> model) {
        return IntStream.range(0, model.getSize())
                .mapToObj(model::getElementAt)
                .filter(CheckableItem::isSelected)
                .map(Objects::toString)
                .sorted()
                .collect(Collectors.joining(", "));
    }
}

class WindowsCheckedComboBox<E extends CheckableItem> extends CheckedComboBox<E> {
    private transient ActionListener listener;

    protected WindowsCheckedComboBox(ComboBoxModel<E> model) {
        super(model);
    }

    @Override public void updateUI() {
        setRenderer(null);
        removeActionListener(listener);
        super.updateUI();
        listener = e -> {
            if ((e.getModifiers() & AWTEvent.MOUSE_EVENT_MASK) != 0) {
                keepOpen = true;
                updateItem(getSelectedIndex());
            }
        };
        addActionListener(listener);

        JLabel label = new JLabel(" ");
        JCheckBox check = new JCheckBox(" ");
        setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            if (index < 0) {
                String txt = getCheckedItemString(list.getModel());
                label.setText(txt.isEmpty() ? " " : txt);
                return label;
            } else {
                check.setText(Objects.toString(value, ""));
                check.setSelected(value.isSelected());
                if (isSelected) {
                    check.setBackground(list.getSelectionBackground());
                    check.setForeground(list.getSelectionForeground());
                } else {
                    check.setBackground(list.getBackground());
                    check.setForeground(list.getForeground());
                }
                return check;
            }
        });
        initActionMap();
    }
}
