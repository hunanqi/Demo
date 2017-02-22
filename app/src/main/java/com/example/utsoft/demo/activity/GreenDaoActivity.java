package com.example.utsoft.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utsoft.demo.R;
import com.example.utsoft.demo.entity.DaoSession;
import com.example.utsoft.demo.entity.DaoTestEntity;
import com.example.utsoft.demo.entity.DaoTestEntityDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 胡楠启 on 2017/2/21 16:38
 * Function: 数据库greendao测试activity
 * Desc:
 */
public class GreenDaoActivity extends AppCompatActivity {

    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.tv_greenDao)
    TextView mTvGreenDao;
    private DaoTestEntityDao mDao;//greendao数据库操作函数
    private int mIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        //数据库操作类初始化
        MApplication application = (MApplication) getApplication();
        DaoSession daoSession = application.getDaoSession();
        mDao = daoSession.getDaoTestEntityDao();
    }

    @OnClick({R.id.btn_query, R.id.btn_update, R.id.btn_delete, R.id.btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_query://查询
                query();
                break;
            case R.id.btn_update://修改
                DaoTestEntity entity0 = new DaoTestEntity(1L, "hu", "男", String.valueOf(18));
                mDao.update(entity0);
                break;
            case R.id.btn_delete://删除
                mDao.deleteByKey(1L);
                break;
            case R.id.btn_add://添加
                DaoTestEntity entity = new DaoTestEntity(null, "hu", "女", String.valueOf(18 + mIndex));
                mDao.insert(entity);
                mIndex++;
                break;
        }

    }

    //数据库查询操作
    private void query() {
        List<DaoTestEntity> daoTestEntities = mDao.loadAll();
        StringBuilder stringBuilder = new StringBuilder();
        if (daoTestEntities.size() > 0) {
            for (DaoTestEntity data :
                    daoTestEntities) {
                stringBuilder.append("姓名：" + data.getName() + "性别：" + data.getSex() + "id:" +
                        data.getId() + "age:" + data.getAge()+"\n");
            }
            mTvGreenDao.setText(stringBuilder.toString());
        }
    }
}
