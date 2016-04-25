package com.anja.task1.app;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Anna on 16.04.2016.
 */
public class RandomRequestFactory {

    private Random mRandom = new Random();

    private List<String> mTitles = Arrays.asList(
            "Комунальне господарство",
            "Благоустрій та будівництво",
            "Демонтаж інших об'єктів, що входять до переліку",
            "Ремонт та обслуговування",
            "Прибирання та санітарний стан території"); //[Comment] Use string-array

    private List<Integer> mImages = Arrays.asList(
            R.drawable.manhole,
            R.drawable.open_manhole,
            R.drawable.trams,
            R.drawable.donetsk_street,
            R.drawable.luke,
            R.drawable.open_luke);

    private List<Integer> mIcons = Arrays.asList(
            R.drawable.ic_build,
            R.drawable.ic_municipal_economy,
            R.drawable.ic_requst,
            R.drawable.ic_geothermal,
            R.drawable.ic_oil_rig,
            R.drawable.ic_reuse);

    private List<String> mResponsible = Arrays.asList(
            "Дніпропетровський МВК ()",
            "Дніпропетровська облдержадміністрація",
            "ЖКГ Дніпропетровська");

    private List<String> mTexts = Arrays.asList(
            "Открытый люк (возле рекламного щита), район поворота 18 и 19 трамваев на проспект Мира с Донецкого шоссе",
            "Нет заграждений на трамвайной остановке в районе Петровского",
            "Аварийное состояние дороги на Старом мосту");

    private List<String> mAddresses = Arrays.asList(
            "вул. Б.Короткова, 22, Дніпропетровськ",
            "Дніпропетровськ, вул. Олеся Гончара, 10",
            "Дніпропетровськ, проспект Богдана Хмельницького, 5-А");


  public Request generateRequest(Request.Status status){
      Request request = new Request();
      request.setStatus(status);
      request.setTitle(mTitles.get(mRandom.nextInt(mTitles.size())));
      List<Integer> randomImages = new ArrayList<Integer>();
      for (int i = 0; i<4; i++) {
          randomImages.add(mImages.get(mRandom.nextInt(mImages.size())));
      }
      request.setImages(randomImages);
      request.setIcon(mIcons.get(mRandom.nextInt(mIcons.size())));
      request.setResponsible(mResponsible.get(mRandom.nextInt(mResponsible.size())));
      request.setText(mTexts.get(mRandom.nextInt(mTexts.size())));
      request.setLikes(mRandom.nextInt(100));
      request.setAddress(mAddresses.get(mRandom.nextInt(mAddresses.size())));
      request.setDays((mRandom.nextInt(10)+2)+" днів");
      DateTime randomCreateDate = new DateTime(2016, mRandom.nextInt(12)+1, mRandom.nextInt(29)+1, 0, 0);
      request.setCreateDate(randomCreateDate);
      request.setRegisterDate(randomCreateDate.plusDays(1));
      request.setDeadlineDate(randomCreateDate.plusDays(mRandom.nextInt(10) + 1));
      return request;
  }
}
