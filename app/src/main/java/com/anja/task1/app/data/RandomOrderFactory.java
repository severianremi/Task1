package com.anja.task1.app.data;

import com.anja.task1.app.R;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Anna on 16.04.2016.
 */
public class RandomOrderFactory {

    private Random mRandom = new Random();

    private String[] mTitles = {
            "Комунальне господарство",
            "Благоустрій та будівництво",
            "Демонтаж інших об'єктів, що входять до переліку",
            "Ремонт та обслуговування",
            "Прибирання та санітарний стан території"};

    private int[] mImages = {
            R.drawable.manhole,
            R.drawable.open_manhole,
            R.drawable.trams,
            R.drawable.donetsk_street,
            R.drawable.luke,
            R.drawable.open_luke};

    private int[] mIcons = {
            R.drawable.ic_build,
            R.drawable.ic_municipal_economy,
            R.drawable.ic_requst,
            R.drawable.ic_geothermal,
            R.drawable.ic_oil_rig,
            R.drawable.ic_reuse};

    private String[] mResponsible = {
            "Дніпропетровський МВК ()",
            "Дніпропетровська облдержадміністрація",
            "ЖКГ Дніпропетровська"};

    private String[] mTexts = {
            "Открытый люк (возле рекламного щита), район поворота 18 и 19 трамваев на проспект Мира с Донецкого шоссе",
            "Нет заграждений на трамвайной остановке в районе Петровского",
            "Аварийное состояние дороги на Старом мосту"};

    private String[] mAddresses = {
            "вул. Б.Короткова, 22, Дніпропетровськ",
            "Дніпропетровськ, вул. Олеся Гончара, 10",
            "Дніпропетровськ, проспект Богдана Хмельницького, 5-А"};


  public Order generateOrder(Order.Status status){
      Order order = new Order();
      order.setStatus(status);
      order.setTitle(mTitles[mRandom.nextInt(mTitles.length)]);
      List<Integer> randomImages = new ArrayList<Integer>();
      for (int i = 0; i<4; i++) {
          randomImages.add(mImages[mRandom.nextInt(mImages.length)]);
      }
      order.setImages(randomImages);
      order.setIcon(mIcons[mRandom.nextInt(mIcons.length)]);
      order.setResponsible(mResponsible[mRandom.nextInt(mResponsible.length)]);
      order.setText(mTexts[mRandom.nextInt(mTexts.length)]);
      order.setLikes(mRandom.nextInt(100));
      order.setAddress(mAddresses[mRandom.nextInt(mAddresses.length)]);
      order.setDays((mRandom.nextInt(10)+2)+" днів");
      DateTime randomCreateDate = new DateTime(2016, mRandom.nextInt(12)+1, mRandom.nextInt(29)+1, 0, 0);
      order.setCreateDate(randomCreateDate);
      order.setRegisterDate(randomCreateDate.plusDays(1));
      order.setDeadlineDate(randomCreateDate.plusDays(mRandom.nextInt(10) + 1));
      return order;
  }
}
