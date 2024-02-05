package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Семинар №1
//Задача 1: Добавить на экран компоновщик-сетку с одним столбцом и
//        добавить над существующей кнопкой следующие компоненты в заданном
//        порядке: JLabel с заголовком «Выберите режим игры», сгруппированные
//        в ButtonGroup переключаемые JRadioButton с указанием режимов «Человек
//        против компьютера» и «Человек против человека», JLabel с заголовком
//        «Выберите размеры поля», JLabel с заголовком «Установленный размер
//        поля:», JSlider со значениями 3..10, JLabel с заголовком «Выберите длину
//        для победы», JLabel с заголовком «Установленная длина:», JSlider со
//        значениями 3..10.
public class SettingsWindow extends JFrame {
    public static final int WINDOW_HEIGHT = 230;
    public static final int WINDOW_WIDTH = 350;
    int maxWinLength = 2;
    JButton btnStart = new JButton("Start new game");
    JPanel panel = new JPanel(new GridLayout(3, 1));

    JPanel bottomPanel = new JPanel(new BorderLayout());
    JSlider sliderSize, sliderWinLength;
    JRadioButton humanButton, aiButton;


    public SettingsWindow(GameWindow gameWindow) {
        setLocationRelativeTo(gameWindow);
        setLocation(getX() - WINDOW_WIDTH / 2, getY() - WINDOW_HEIGHT / 2);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Settings");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                int mode = aiButton.isSelected() ? 1 : 0;
                gameWindow.startNewGame(mode, sliderSize.getValue(), sliderSize.getValue(), sliderWinLength.getValue());

            }
        });

        panel.add(getModePanel());
        panel.add(getFieldSize(3, 10, 3));
        panel.add(getWinLength());


        bottomPanel.add(btnStart);
        add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private JPanel getModePanel() {

        JPanel modePanel = new JPanel(new GridLayout(3, 1));
        JLabel textTitle = new JLabel("Выберите режим игры: ");

        humanButton = new JRadioButton("Человек против человека.");
        aiButton = new JRadioButton("Человек против компьютера.");
        aiButton.setSelected(true);

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(humanButton);
        bgroup.add(aiButton);

        modePanel.add(textTitle);
        modePanel.add(humanButton);
        modePanel.add(aiButton);

        return modePanel;
    }

    private JPanel getFieldSize(int min, int max, int cur) {

        String textSize = "Установленный размер поля: ";

        JPanel sizePanel = new JPanel(new GridLayout(3, 1));
        JLabel textTitle = new JLabel("Выберите размеры поля");

        sliderSize = new JSlider(min, max, cur);
        JLabel textRequest = new JLabel(textSize + sliderSize.getValue());

        // добавляем слушателя на слайдер
        sliderSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                textRequest.setText(textSize + sliderSize.getValue());
                sliderWinLength.setMaximum(sliderSize.getValue());
            }
        });

        sizePanel.add(textTitle);
        sizePanel.add(textRequest);
        sizePanel.add(sliderSize);

        return sizePanel;
    }

    private JPanel getWinLength() {

        String textSize = "Установленная длина для победы: ";

        JPanel sizePanel = new JPanel(new GridLayout(3, 1));
        JLabel textTitle = new JLabel("Выберите длину для победы");

        sliderWinLength = new JSlider(2, sliderSize.getValue(), sliderSize.getValue());

        JLabel textRequest = new JLabel(textSize + sliderWinLength.getValue());

        // добавляем слушателя на слайдер
        sliderWinLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                textRequest.setText(textSize + sliderWinLength.getValue());
            }
        });

        sizePanel.add(textTitle);
        sizePanel.add(textRequest);
        sizePanel.add(sliderWinLength);

        return sizePanel;
    }


}
