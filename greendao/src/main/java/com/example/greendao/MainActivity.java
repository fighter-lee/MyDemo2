package com.example.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.greendao.query.Join;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    private DeviceInfoDao deviceInfoDao;
    private AreaDao areaDao;
    private CityDao cityDao;
    private CountryDao countryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "device_info", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        //        deviceInfoDao = daoSession.getDeviceInfoDao();
        areaDao = daoSession.getAreaDao();
        cityDao = daoSession.getCityDao();
        countryDao = daoSession.getCountryDao();

        insert();

        update();
    }

    private void insert() {
        //        deviceInfoDao.insert(info);
        cityDao.insertInTx(new City("Shanghai", 100000L), new City("cs", 100L), new City("beij", 10000L));
        //        areaDao.insertInTx(new Area(""));
        countryDao.insertInTx(new Country("CN", 100000000L, "Shanghai"), new Country("En", 10000000L, "cs"), new Country("An", 1000000L, "zzz"));
    }

    private void delet() {
        //        deviceInfoDao.deleteByKeyInTx();
    }

    private void update() {
        //查询所有国家中人数大于100的城市
        QueryBuilder<City> cityQueryBuilder = cityDao.queryBuilder().where(CityDao.Properties.Population.gt(100L));
        Join<City, Country> country = cityQueryBuilder.join(Country.class, CountryDao.Properties.Name);
        country.where(CountryDao.Properties.Name.like("%n%"));
        List<City> list = cityQueryBuilder.list();
        for (City city : list) {
            Log.i("MainActivity", city.getName());
        }
    }
}
