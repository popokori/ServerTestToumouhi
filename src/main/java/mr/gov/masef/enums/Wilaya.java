package mr.gov.masef.enums;

import java.util.Arrays;
import java.util.List;

public enum Wilaya {
    ADRAR(1, "Adrar", "أدرار", Arrays.asList(
            new Moughataa(1, "Atar", "أطار"),
            new Moughataa(2, "Chinguetti", "شنقيط"),
            new Moughataa(3, "Ouadane", "وادان"),
            new Moughataa(4, "Aoujeft", "أوجفت")
    )),
    ASSABA(2, "Assaba", "عصابة", Arrays.asList(
            new Moughataa(1, "Barkéol", "بركويل"),
            new Moughataa(2, "Guerou", "كرو"),
            new Moughataa(3, "Kiffa", "كيفة"),
            new Moughataa(4, "Kankoussa", "كنكوصة"),
            new Moughataa(5, "Boumdeid", "بومديد")
    )),
    BRAKNA(3, "Brakna", "البراكنة", Arrays.asList(
            new Moughataa(1, "Aleg", "ألاك"),
            new Moughataa(2, "Bababé", "بابابي"),
            new Moughataa(3, "Boghé", "بوغي"),
            new Moughataa(4, "Magtalahjar", "مقطع لحجار"),
            new Moughataa(5, "M'Bagne", "مبان"),
            new Moughataa(6, "Male", "مال")
    )),
    DAKHLET_NOUADHIBOU(4, "Dakhlet Nouadhibou", "داخلت انواذيبو", Arrays.asList(
            new Moughataa(1, "Nouadhibou", "نواذيبو"),
            new Moughataa(2, "Chami", "الشامي")
    )),
    GORGOL(5, "Gorgol", "كوركول", Arrays.asList(
            new Moughataa(1, "Kaédi", "كيهيدي"),
            new Moughataa(2, "Monguel", "مونغل"),
            new Moughataa(3, "Lexeiba1", "لكصيبة1"),
            new Moughataa(4, "Maghama", "مقامة"),
            new Moughataa(5, "M'Bout", "مبوت")
    )),
    GUIDIMAKA(6, "Guidimaka", "كيديماغا", Arrays.asList(
            new Moughataa(1, "Wompou", "وامبو"),
            new Moughataa(2, "Ghabou", "غابو"),
            new Moughataa(3, "Ould Yengé", "ولد ينجه"),
            new Moughataa(4, "Sélibaby", "سيلبابي")
    )),
    HODH_ECH_CHARGUI(7, "Hodh ech Chargui", "الحوض الشرقي", Arrays.asList(
            new Moughataa(1, "Néma", "النعمة"),
            new Moughataa(2, "Timbedra", "تمبدغة"),
            new Moughataa(3, "Djiguenni", "جيكني"),
            new Moughataa(4, "Bassiknou", "باسكنو"),
            new Moughataa(5, "Amourj", "أمرج"),
            new Moughataa(6, "Adelbegrou", "عدل بكرو"),
            new Moughataa(7, "Oualata", "ولاته"),
            new Moughataa(8, "N'beiket Lahwach", "بكيت لحواش")
    )),
    HODH_EL_GHARBI(8, "Hodh El Gharbi", "الحوض الغربي", Arrays.asList(
            new Moughataa(1, "Koubenni", "كوبني"),
            new Moughataa(2, "Tintane", "تيمتن"),
            new Moughataa(3, "Aïoun", "عيون العتروس"),
            new Moughataa(4, "Tamcheket", "تيمشكت"),
            new Moughataa(5, "Touil", "تويل")
    )),
    INCHIRI(9, "Inchiri", "إنشيري", Arrays.asList(
            new Moughataa(1, "Bennechab", "بنشاب"),
            new Moughataa(2, "Akjoujt", "أكجوجت")
    )),
    TAGANT(10, "Tagant", "تكانت", Arrays.asList(
            new Moughataa(1, "Moudjeria", "مجرية"),
            new Moughataa(2, "Tidjikja", "تجكجة"),
            new Moughataa(3, "Tichit", "تيشيت")
    )),
    TIRIS_ZEMMOUR(11, "Tiris Zemmour", "تيرس زمور", Arrays.asList(
            new Moughataa(1, "Zoueratt", "الزويرات"),
            new Moughataa(2, "F'Deirick", "افديرك"),
            new Moughataa(3, "Bir Moghrein", "بير أم اغرين")
    )),
    TRARZA(12, "Trarza", "ترارزة", Arrays.asList(
            new Moughataa(1, "Boutilimit", "بوتلميت"),
            new Moughataa(2, "Ouad Naga", "واد الناقة"),
            new Moughataa(3, "Rosso", "روصو"),
            new Moughataa(4, "Mederdra", "مدر"),
            new Moughataa(5, "R'Kiz", "اركيز"),
            new Moughataa(6, "Keur Macène", "كرمسين"),
            new Moughataa(7, "Tekane", "تگاني")
    )),
    NOUAKCHOTT_NORD(13, "Nouakchott Nord", "نواكشوط الشمالية", Arrays.asList(
            new Moughataa(1, "Toujounine", "توجنين"),
            new Moughataa(2, "Teyaret", "تيارت"),
            new Moughataa(3, "Dar Naïm", "دار النعيم")
    )),
    NOUAKCHOTT_SUD(14, "Nouakchott Sud", "نواكشوط الجنوبية", Arrays.asList(
            new Moughataa(1, "Arafat", "عرفات"),
            new Moughataa(2, "El Mina", "الميناء"),
            new Moughataa(3, "Riyad", "الرياض")
    )),
    NOUAKCHOTT_OUEST(15, "Nouakchott Ouest", "نواكشوط الغربية", Arrays.asList(
            new Moughataa(1, "Tevragh Zeina", "تفرغ زينة"),
            new Moughataa(2, "Ksar", "لكصر"),
            new Moughataa(3, "Sebkha", "السبخة")
    ));

    private final int id;
    private final String nameFr;
    private final String nameAr;
    private final List<Moughataa> moughataas;

    Wilaya(int id, String nameFr, String nameAr, List<Moughataa> moughataas) {
        this.id = id;
        this.nameFr = nameFr;
        this.nameAr = nameAr;
        this.moughataas = moughataas;
    }

    public int getId() {
        return id;
    }

    public String getNameFr() {
        return nameFr;
    }

    public String getNameAr() {
        return nameAr;
    }

    public List<Moughataa> getMoughataas() {
        return moughataas;
    }

    public static class Moughataa {
        private final int id;
        private final String nameFr;
        private final String nameAr;

        public Moughataa(int id, String nameFr, String nameAr) {
            this.id = id;
            this.nameFr = nameFr;
            this.nameAr = nameAr;
        }

        public int getId() {
            return id;
        }

        public String getNameFr() {
            return nameFr;
        }

        public String getNameAr() {
            return nameAr;
        }
    }
}
