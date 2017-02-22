package com.example.utsoft.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utsoft.demo.R;
import com.example.utsoft.demo.entity.RealmEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by 胡楠启 on 2017/2/22 11:41
 * Function: realm 数据库框架测试
 * Desc:
 */
public class RealmActivity extends AppCompatActivity {

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
    private Realm mRealm;//realm操作变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        mRealm=Realm.getDefaultInstance();//初始化
    }

    @OnClick({R.id.btn_query, R.id.btn_update, R.id.btn_delete, R.id.btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_query://查询
                query();
                break;
            case R.id.btn_update://修改
                update();
                break;
            case R.id.btn_delete://删除
                delete();
                break;
            case R.id.btn_add://添加
                add();
                break;
        }
    }

    /**
     * 添加数据
     */
    private void add(){
        mRealm.beginTransaction();
        RealmEntity country1 = mRealm.createObject(RealmEntity.class);
        country1.setAge("18");
        country1.setId(1l);
        country1.setName("小红");
        country1.setSex("女");
        mRealm.commitTransaction();
    }
    /**
     * 查询,这里是全部查询
     */
    private void query(){
        RealmResults<RealmEntity> all = mRealm.where(RealmEntity.class).findAll();
        StringBuilder stringBuilder=new StringBuilder();
        for (RealmEntity entity:all){
            stringBuilder.append("姓名：" + entity.getName() + "性别：" + entity.getSex() + "id:" +
                    entity.getId() + "age:" + entity.getAge()+"\n");
        }
      mTvGreenDao.setText(stringBuilder.toString()+"");
    }
    /**
     * 删除第一条数据
     */
    private void delete(){
        final RealmResults<RealmEntity> all = mRealm.where(RealmEntity.class).findAll();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                all.deleteFirstFromRealm();//删除第一条数据
            }
        });
    }
    /**
     * 修改数据
     */
    private void update(){
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<RealmEntity> all = mRealm.where(RealmEntity.class).findAll();
                for (RealmEntity entity:all) {
                    entity.setSex("男");
                }
            }
        });
    }
}
