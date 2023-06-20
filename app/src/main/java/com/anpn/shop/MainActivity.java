package com.anpn.shop;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;


import com.anpn.shop.adapter.MyAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;

/**
 * @author Anikeeva P.D.
 */
public class MainActivity extends AppCompatActivity {
    ArrayList<Product> products = new ArrayList<>();


    Button btPizza, btCombo, btDessert, btBeverages;
    ImageButton imQr;

    BottomNavigationView bottomNavigationMenuView;


    @SuppressLint({"UseSupportActionBar", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //инициализация списка
        RecyclerView recyclerView = findViewById(R.id.rcView);
        //адаптер
        MyAdapter adapter = new MyAdapter(this, products);
        //установка адаптера
        recyclerView.setAdapter(adapter);

        //получение экземпляра элемента Spinner
        Spinner spinner = findViewById(R.id.sp);

        //настройка адаптера
        ArrayAdapter<?> adapterSp =
                ArrayAdapter.createFromResource(this, R.array.cities,
                        android.R.layout.simple_spinner_item);
        adapterSp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //вызов адаптера
        spinner.setAdapter(adapterSp);

        //выбор категории товара
        btPizza = findViewById(R.id.btPizza);
        btCombo = findViewById(R.id.btCombo);
        btDessert = findViewById(R.id.btDessert);
        btBeverages = findViewById(R.id.btBeverages);

        btPizza.setOnClickListener(v_ -> {
            setInitialDataPizza();
            //установка адаптера
            recyclerView.setAdapter(adapter);
        });

        btCombo.setOnClickListener(v_ -> {
            setInitialDataCombo();
            recyclerView.setAdapter(adapter);
        });
        btDessert.setOnClickListener(v_ -> {
            setInitialDataDessert();
            recyclerView.setAdapter(adapter);
        });
        btBeverages.setOnClickListener(v_ -> {
            setInitialDataBeverages();
            recyclerView.setAdapter(adapter);
        });

        //навигациия по нижней панели меню

        bottomNavigationMenuView = findViewById(R.id.btNavView);

        MenuFragment menuFragment = new MenuFragment();
        AccFragment accFragment = new AccFragment();
        DelFragment delFragment = new DelFragment();

        //слушатель нажатий кнопок меню
        bottomNavigationMenuView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.btMenu:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.constraintLayout, menuFragment)
                            .commit();
                    return true;

                case R.id.btAcc:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.constraintLayout, accFragment)
                            .commit();
                    return true;

                case R.id.btDel:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.constraintLayout, delFragment)
                            .commit();
                    return true;
            }
            return false;
        });


        //панель сканирования QR кода
        imQr = findViewById(R.id.imQr);

        imQr.setOnClickListener(v_ -> scanCode());

    }


    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }


    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result ->
    {
        if (result.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).show();
        }
    });


    private void setInitialDataPizza() {
        products.clear();
        products.add(new Product(R.drawable.cheesy, "Сырная", "Моцарелла, сыры чеддер и пармезан, фирменный соус альфредо", "300р"));
        products.add(new Product(R.drawable.pepperoni_fresh, "Пепперони фреш", "Пикантная пепперони, увеличенная порция моцареллы, томаты, фирменный томатный соус", "400р"));
        products.add(new Product(R.drawable.double_chick, "Двойной цыпленок", "Цыпленок, моцарелла, фирменный соус альфредо", "800р"));
        products.add(new Product(R.drawable.chorizo_fresh, "Чоризо фреш", "Фирменный томатный соус, моцарелла, острая чоризо, сладкий перец", "500р"));
        products.add(new Product(R.drawable.ham_and_cheese, "Ветчина и сыр", "Ветчина, моцарелла, фирменный соус альфредо", "400р"));
        products.add(new Product(R.drawable.carbonara, "Карбонара", "Бекон, сыры чеддер и пармезан, моцарелла, томаты, красный лук, чеснок, фирменный соус альфредо, итальянские травы", "800р"));
        products.add(new Product(R.drawable.cheese_chicken, "Сырный цыпленок", "Цыпленок, моцарелла, сыры чеддер и пармезан, сырный соус, томаты, фирменный соус альфредо, чеснок", "600р"));
        products.add(new Product(R.drawable.pesto, "Песто", "Цыпленок, соус песто, кубики брынзы, томаты, моцарелла, фирменный соус альфредо", "500р"));
        products.add(new Product(R.drawable.meat, "Мясная", "Цыпленок, ветчина, пикантная пепперони, острая чоризо, моцарелла, фирменный томатный соус", "400р"));
        products.add(new Product(R.drawable.arriva, "Аррива!", "Острая чоризо, цыпленок, томаты, соус бургер, сладкий перец, красный лук, моцарелла, соус ранч, чеснок", "700р"));
        products.add(new Product(R.drawable.home, "Домашняя", "Пикантная пепперони, ветчина, маринованные огурчики, томаты, моцарелла, фирменный томатный соус", "400р"));
        products.add(new Product(R.drawable.vegetables_and_mushrooms, "Овощи и грибы", "Шампиньоны, томаты, сладкий перец, красный лук, кубики брынзы, моцарелла, фирменный томатный соус, итальянские травы", "400р"));
        products.add(new Product(R.drawable.rustic_with_boiled_pork, "Деревенская с бужениной", "Запеченная буженина из свинины, моцарелла, картофель из печи, маринованные огурчики, красный лук, чеснок, фирменный томатный соус, соус ранч", "600р"));
    }

    private void setInitialDataCombo() {
        products.clear();
        products.add(new Product(R.drawable.combo_3, "Комбо", "Маленькая пицца, закуска, напиток и соус.", "499р"));
        products.add(new Product(R.drawable.combo, "3 пиццы", "Три удовольствия в нашем меню — это 3 средние пиццы на ваш выбор.", "1099р"));
    }

    private void setInitialDataDessert() {
        products.clear();
        products.add(new Product(R.drawable.dessert, "Бруслетики", "Это задорные сладкие рулетики, в которых закрутился микс из натуральной брусники и сгущенного молока", "200р"));

    }

    private void setInitialDataBeverages() {
        products.clear();
        products.add(new Product(R.drawable.cola, "Добрый Кола", "0,5л.", "100р"));
        products.add(new Product(R.drawable.cola0, "Добрый Кола без сахара", "0,5л.", "100р"));

    }

}