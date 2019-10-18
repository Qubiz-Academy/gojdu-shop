package ro.gojdu.shop.ui.main.ui.shop;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.Collections;

import ro.gojdu.shop.R;
import ro.gojdu.shop.ui.main.ui.mycart.ShopSession;

public class ShopFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    private LinearLayout linearLayout;
    private BottomSheetBehavior bottomSheetBehavior;
    private TextView tvFilter;
    private TextView tvFilterAZ;
    private TextView tvFilterZA;
    private TextView tvFilterPriceDown;
    private TextView tvFilterPriceUp;
    private TextView tvPriceRange;
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Product> productsFull;
    private SearchView searchView;
    private EditText et1;
    private EditText et2;
    private InputMethodManager imm;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = root.findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new ProductAdapter(this.getActivity(), products);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_shop, container, false);
        getProducts();
        sortAZ(true);
        imm = (InputMethodManager) this.getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        linearLayout = root.findViewById(R.id.bottom);
        searchView = root.findViewById(R.id.svSearch);
        tvFilterAZ = root.findViewById(R.id.tvFilterAZ);
        tvFilterZA = root.findViewById(R.id.tvFilterZA);
        tvFilterPriceDown = root.findViewById(R.id.tvFilterPriceDown);
        tvFilterPriceUp = root.findViewById(R.id.tvFilterPriceUp);
        tvPriceRange = root.findViewById(R.id.tvPriceRange);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        tvFilter = root.findViewById(R.id.tvFilter);
        et1 = root.findViewById(R.id.et1);
        et2 = root.findViewById(R.id.et2);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                myAdapter.getFilter().filter(s);
                return false;
            }
        });
        tvFilterAZ.setOnClickListener(view -> {
            sortAZ(true);
            myAdapter.notifyDataSetChanged();
            keyboardClose();
        });
        tvFilterZA.setOnClickListener(view -> {
            sortAZ(false);
            myAdapter.notifyDataSetChanged();
            keyboardClose();
        });
        tvFilterPriceDown.setOnClickListener(view -> {
            sortPrice(true);
            myAdapter.notifyDataSetChanged();
            keyboardClose();
        });
        tvFilterPriceUp.setOnClickListener(view -> {
            sortPrice(false);
            myAdapter.notifyDataSetChanged();
            keyboardClose();
        });
        tvPriceRange.setOnClickListener(view -> {
            double min,max;
            String smin,smax;
            try {
                smin=et1.getText().toString();
                smax=et2.getText().toString();
                if(!smin.matches(""))
                    min = Double.parseDouble(smin);
                else
                    min=0;
                if(!smax.matches(""))
                    max = Double.parseDouble(smax);
                else
                    max=-1;
                sortPriceRange(min,max);
                myAdapter.notifyDataSetChanged();
            } catch(NumberFormatException nfe){
                et1.setText(null);
                et2.setText(null);
            }
            keyboardClose();
        });
        tvFilter.setOnClickListener(view -> {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN)
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            else
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        });
        return root;
    }

    private void keyboardClose(){
        imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
        Handler handler = new Handler();
        handler.postDelayed(() -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN),100);
    }

    private void getProducts()
    {
        products.add(new Product("Computer 2","Very good better than 2nd.",200.00));
        products.add(new Product("Computer 4","Very good better than 1st.aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaAlso pretty meh!",30000.00));
        products.add(new Product("Computer 3","Pretty meh.",0.00));
        products.add(new Product("Computer 1 with a name too long to display","Quite average.",1000.00));
        products.add(new Product("Laptop HP 15-ra060nq cu procesor Intel® Celeron® N3060 pana la 2.48 GHz, 15.6\", 4GB, 500GB, DVD-RW, Intel® HD Graphics 400, FreeDOS, Black","Performante fiabile. Design minunat. Faceti mai multe.\nObtineti zilnic un randament ridicat, cu un laptop elegant, creat pentru a va mentine conectat si a va permite sa detineti controlul asupra activitatilor zilnice. Beneficiind de performante fiabile si de un acumulator cu durata mare de viata, este simplu sa navigati, sa transmiteti si sa fiti conectat cu ceea ce conteaza cel mai mult.\nAcest laptop elegant ofera performante fiabile si o durata mare de viata a acumulatorului, astfel incat va este simplu sa navigati, sa transmiteti si sa fiti conectat cu ceea ce conteaza cel mai mult.\nPC-ul pe care va puteti baza.\nCele mai recente procesoare Intel va garanteaza performantele fiabile de care aveti nevoie pentru lucru si divertisment. Bucurati-va de o durabilitate ridicata, pe un laptop conceput pentru a face cu usurinta ceea ce doriti.\nConectati-va cu ceea ce conteaza.\nBucurati-va de divertisment si ramaneti conectat cu prietenii si familia, gratie unui afisaj bogat, HD sau FHD la anumite modele si uneicamere HD la anumite modele. In plus, stocati cu usurinta si va bucurati de muzica, filmele si fotografiile preferate.\nConceput atent. In interior si la exterior.\nFiind frumos conceput, atat in interior, cat si la exterior, acest laptop HP cu diagonala de 39,6 cm (15,6\") se potriveste perfect cu stilul dumneavoastra de viata. Modelele vesele, texturile unice si balamaua placata cu crom (la anumite modele) adauga un strop de culoare fiecarei zile.",899.99,"https://s12emagst.akamaized.net/products/13634/13633476/images/res_6f78239429472e5fd42dfa42b850e78c_450x450_vo8a.jpg"));
        products.add(new Product("Laptop Dell Inspiron 3580 cu procesor Intel® Core™ i5-8265U pana la 3.90 GHz, Whiskey Lake, 15.6\", Full HD, 8GB, 256GB SSD, AMD Radeon 520 2GB, Linux, Black0","CinemaColor.\nImaginile sunt la fel de vii ca realitatea inconjuratoare, pixel cu pixel. Bucurati-va de culori si detalii mai bogate si mai vii cu profilurile de culoare Dell.\nCinemaStream.\nPlaca de retea wireless SmartByte va ofera canale cu latime de banda maxima pentru o experienta fluida, fara sacadare atunci cand vizionati videoclipuri sau ascultati muzica.\nCinemaSound.\nTehnologia Waves MaxxAudio® Pro intensifica volumul si va ofera sunete extrem de clare, pentru a beneficia de calitate audio la nivel de studio.\nImpresionati de departe prin stil.\nAratati extraordinar in deplasari: acest laptop portabil este usor de transportat oriunde, iar aspectul sau exterior elegant il face un accesoriu atragator.\nProfitati la maximum de fiecare zi.\nProcesoare capabile: procesoarele Intel® asigura o viteza de reactie incredibila si multitasking lin si fara probleme.\nMemorie si capacitate de stocare mari: comutati cu usurinta intre aplicatiile deschise, cu memoria de 8GB. O unitate SSD cu spatiu de stocare de 256 GB va ajuta sa detineti controlul asupra fisierelor dvs..\nEcran uimitor: afisajul FullHD antireflex va ofera o imagine clara, luminoasa, care nu oboseste ochii.\nGrafica spectaculoasa: bucurati-va de un plus de energie si de performanta zilnica suplimentara oferita de placa grafica dedicata GDDR5 de 2GB.\nFlexibilitate pe masura necesitatilor.\nO gama larga de porturi: conectati-va la televizor sau la monitor prin intermediul portului HDMI, descarcati fotografii folosind slotul pentru carduri SD si beneficiati de viteze mari de transfer de la toate accesoriile datorita celor doua porturi USB 3.1 din prima generatie.\nTastatura numerica: alocarea bugetelor si alte calcule sunt floare la ureche datorita tastaturii numerice.\nUniti dispozitivele cu Dell Mobile Connect.\nIntegrare perfecta intre PC si smartphone: accesati mai multe dispozitive fara a va imparti atentia. Dell Mobile Connect va asociaza smartphone-ul cu iOS sau Android cu laptopul.\nLimitati perturbarile: cu Dell Mobile Connect, aveti posibilitatea de a prelua notificari din apeluri telefonice, SMS-uri, mesaje instantanee si alte aplicatii direct pe laptop. Concentrati-va la un singur ecran si raspundeti numai atunci cand va convine.\nConfidentialitate perfect protejata: telefonul se conecteaza la PC prin conexiunea securizata, punct la punct, oferita de Dell Mobile Connect, astfel incat datele dvs. sa nu fie niciodata expuse prin conexiuni prin internet nesecurizate.\nPorturi si sloturi.\n1. Cititor de carduri SD | 2. Port USB 2.0 | 3. Unitate de disc optica | 4. Slot pentru incuietoare in forma de pana | 5. Alimentare | 6. Port HDMI 1.4b | 7. Port RJ-45 | 8. Port USB 3.1 gen. 1 | 9. Port USB 3.1 gen.1 | 10. Mufa audio pentru casti si microfon.\nDesign inspirat de utilizatori\nFiecare Inspiron este proiectat cu atentie, pentru a asigura performante fiabile si dupa de ani de zile de utilizare regulata. Am testat riguros sistemele noastre pentru a va imbunatati experienta.\nCaldura si frig.\nChiar si dupa expunerea la temperaturi intre -40 °C/-40 °F si 65 °C/149 °F, Inspiron isi mentine performantele.\nTaste si balamale.\nButoanele noastre de alimentare sunt testate sa reziste fara defect la 40.000 de apasari, iar tastele tastaturii la 5 milioane de apasari. Deschis, inchis sau rasucit – baza si capacul modelului Inspiron au fost testate de zeci de mii de ori.\nElemente interne.\nTestam de zeci de ori conectorii interni ai componentelor, de la USB la alimentare sau baterii, pentru a asigura performanta de durata si inlocuire facila.",2299.99,"https://s12emagst.akamaized.net/products/21035/21034576/images/res_9398989b1e422cd0c1c461d6513391c8_200x200_a3r.jpg"));
        products.add(new Product("Laptop ASUS VivoBook 15 X540MA-GO145 cu procesor Intel® Celeron® N4000 pana la 2.60 GHz, 15.6\", 4GB, 500GB, DVD-RW, Intel® UHD Graphics 600, Endless OS, Chocolate Black","Proiectate pentru Productivitate si Divertisment.\nLaptop-urile din Seria ASUS X540 sunt echipate cu cele mai recente procesoare, pentru un nivel foarte bun de performanta in orice situatie. Dotate cu un controller avansat pentru modulele de memorie, 4GB de memorie RAM, X540 este platforma ideala pentru utilizarea zilnica.\nSunet Expansiv, Optimizat de Experti.\nO combinatie de componente hardware, software, si optimizare atenta, tehnologia SonicMaster a fost dezvoltata cu scopul declarat de a va oferi cel mai bun sunet disponibil in cazul unui notebook. Un codec profesionist asigura o performanta sonora cu un nivel de precizie foarte ridicat, un amplificator optimizat permite redarea sunetului la un volum superior, in timp ce difuzoarele si camerele de rezonanta mai largi suporta un sunet mai puternic si bass mai profund. Elementele suplimentare de procesare a semnalului audio ajuta la optimizarea performantelor componentelor hardware, filtrand zgomotul si imbunatatind claritatea audio astfel incat sa va puteti bucura de un sunet de o claritate exceptionala pe modelele voastre din Seria X.\nDesign special al difuzoarelor pentru sunet de calitate superioara.\nX540 este echipat cu difuzoare rotunde ce maximizeaza fiecare milimetru cubic disponibil in carcasa pentru a va oferi un nivel mai bun de performanta la frecvente joase si zgomot redus. Camera de rezonanta de dimensiuni mari (19.4cc) ofera un bass mai bun si o claritate sonora excelenta.\nOptimizare Sonora cu AudioWizard.\nAudioWizard va ajuta sa optimizati sunetul cu ajutorul a cinci moduri de auditie pre-setate. Modul Muzica va aduce melodiile favorite la viata, in timp ce modul Filme va ofera un sunet de calitate cinematografica. Modul de Inregistrare optimizeaza setarile pentru ca voi sa beneficiati de o calitate excelenta a inregistrarilor. Modul pentru Jocuri va ajuta sa luati parte la actiunea de pe ecran, in timp ce modul Voce asigura un nivel ridicate de claritate in dialoguri.\nFinisaje Elegante. Design Ultra-Subtire.\nASUS X540 este echipat cu o carcasa solida si usoara, ce cantareste doar 2kg – fiind solutia ideala pentru utilizare portabila. Finisajul sau premium va atrage cu siguranta atentia.\nExperimenteaza o Lume a Culorilor cu Tehnologia ASUS Splendid.\nPentru a-ti oferi doar imagini de o calitate excelenta, tehnologia exclusiva ASUS Splendid include un sistem de corectie a temperaturii de culoare, ce permite reproducerea unei palete de culori mai bogate si profunde. Aceasta dispune de patru moduri de afisare vizuale, ce pot fi accesate printr-o singura apasare de buton. Modul Culori Puternice optimizeaza contrastul pentru vizualizarea de fotografii sau urmarirea de clipuri video si filme; Modul Eye Care reduce nivelul de intensitate al luminii albastre si este ideal pentru citit pe perioade lungi de timp. Modul Normal a fost optimizat pentru sarcini de utilizare zilnica; in timp ce Modul Manual este utilizat pentru ajustari avansate ale culorilor.\nProtejeaza-ti Ochii cu ASUS Eye Care.\nMajoritatea panourilor LED emit lumina albastra – principala cauza a problemelor retinei si a fenomenului de degenerare maculara. Modul ASUS Eye Care reduce in mod eficient nivelul de lumina albastra cu 33%, oferind astfel un nivel ridicat de confort la citire si protejandu-va impotriva oboselii ochilor sau a altor probleme ce pot aparea.\nDesign Ergonomic al Tastaturii.\nTastarea este mai confortabila ca niciodata cu ajutorul tastaturii complete de tip chiclet, fabricata dintr-o singura bucata. Un proces imbunatatit de asamblare face posibila atingerea unei curse de doar 1.8mm pentru taste si minimalizarea fenomenului de ”plutire” al acestora, pentru o experienta de tastare mult mai solida.\nTehnologie Intuitiva de Control ASUS Smart Gesture.\nTehnologia ASUS Smart Gesture utilizeaza o combinatie inteligenta de componente hardware si optimizare la nivel software pentru a va oferi o precizie ridicata in controlul dispozitivului. Utilizarea unor tehnici sofisticate de productie, imprumutate de la ecranele tactile pentru smartphone-uri, face posibila atingerea unui nivel foarte ridicat de sensibilitate at touch pad-ului, ce va permite sa faceti zoom sau sa navigati printre diferitele pagini web cu foarte mare usurinta.\nRece Chiar si Dupa Ore Intregi de Utilizare.\nTehnologia ASUS IceCool le ofera modelelor din Seria X un design intern unic ce rezolva problemele de confort cauzate de supraincalzire prin prevenirea acumularii nedorite de caldura in zona suportului pentru palme. Aceasta pastreaza o temperatura de 28°C - 35°C la suprafata suportului pentru palme – semnificativ mai mica decat temperatura normala a corpului uman. Aceasta structura interna exclusiva plaseaza componentele generatoare de caldura la distanta maxima fata de punctul de interactiune cu utilizatorul si, prin utilizarea sistemului performant de racire cu radiatoare si orificii de aerisire, va ofera un nivel ridicat de confort chiar si in cazul utilizarii pe termen lung.",989.99,"https://s12emagst.akamaized.net/products/17468/17467064/images/res_24128be16feb5fad791d7076f06f05a0_200x200_f8bg.jpg"));
        products.add(new Product("Laptop Gaming OMEN by HP 15-dc0014nq cu procesor Intel® Core™ i7-8750H pana la 4.10 GHz, Coffee Lake, 15.6\", Full HD, IPS, 8GB, 1TB, NVIDIA® GeForce® GTX 1050 Ti 4GB, Free DOS, Black","Puternic. Portabil. Nimic nu te tine pe loc.\nAi locuri de vizitat, ai jocuri de jucat si ai putere de demonstrat. Cu laptopul OMEN by HP 15, poti juca oriunde cat poti de bine – fara a sacrifica performantele. Mergi inainte si imbunatateste-ti abilitatile pe un dispozitiv compact, portabil, proiectat pentru a-ti oferi performante grafice din clasa desktopurilor, antren total si facilitati de upgrade.\nJoaca oriunde cat poti de bine – fara a sacrifica performantele. Cu laptopul OMEN by HP 15 mergi inainte si te perfectionezi, gratie performantelor grafice din clasa desktopurilor, capacitatii de antren si facilitatilor de upgrade.\nPutere proiectata pentru a te propulsa inainte.\nCu placi grafice de top NVIDIA GeForce GTX seria 10 si cu un procesor Intel Core din a 8-a generatie si o solutie termica imbunatatita, ai toata puterea necesara pentru a-ti perfectiona abilitatile oriunde.\nFiecare detaliu, sub privirea ta.\nLasa-te captivat de un ecran cu rama ingusta, fie 4K1, fie FHD, cu rata de reimprospatare de 60 Hz sau 144 Hz si de tehnologia NVIDIA G-SYNC, pe anumite modele. O tastatura iluminata pe zone din fundal, si rollover pentru 26 de taste este proiectata strategic pentru a-ti accelera reflexele de joc.\nUpgrade. Extindere. Evolutie.\nLaptopul OMEN 15 este conceput pentru a simplifica upgrade-urile si intretinerea, cu un panou de acces unic la HDD, SSD si RAM. In plus, bucura-te de selectia generoasa de porturi pentru accesoriile si monitoarele tale externe preferate.\nProcesor Intel Core din generatia a 8-a.\nCresteti performantele, cu viteza uniforma de raspuns a sistemului si cu timpii scurti de incarcare, pentru o experienta exceptionala de lucru cu PC-ul. Cu rezolutiile uimitoare, de pana la 4K, redati in flux continutul premium sau chiar creati propriul.",3999.99,"https://s12emagst.akamaized.net/products/16170/16169534/images/res_8cf6a8ac2827c7a7bbc03a6f86f84e92_200x200_le17.jpg"));
        productsFull = new ArrayList<>(products);
        ShopSession.cart.addAll(products);
    }

    private void sortAZ(final boolean az)
    {
        Collections.sort(products, (p1, p2) -> {
            String name1 = p1.getName();
            String name2 = p2.getName();
            if(az)
                return name1.compareTo(name2);
            else
                return name2.compareTo(name1);
        });
    }

    private void sortPrice(final boolean down)
    {
        Collections.sort(products, (p1, p2) -> {
            double price1 = p1.getPrice();
            double price2 = p2.getPrice();
            if(down){
                if(price1<price2)
                    return 1;
                else
                    return -1;
            }
            else{
                if(price1>price2)
                    return 1;
                else
                    return -1;
            }
        });
    }

    private void sortPriceRange(final double min, final double max)
    {
        products.clear();
        for(Product item:productsFull)
        {
            double price = item.getPrice();
            if(min<=price && (price<=max || max==-1.0))
                products.add(item);
        }
        Collections.sort(products, (p1, p2) -> {
            double price1 = p1.getPrice();
            double price2 = p2.getPrice();
            if(price1<price2)
                return 1;
            else
                return -1;
        });
    }
}