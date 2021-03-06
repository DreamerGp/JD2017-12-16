package by.it.kozlov.jd02_01;

class Buyer extends Thread implements IBuyer, IUseBasket {
    private boolean pensioneer = false;

    Buyer(int number) {
        super("Покупатель №" + number);
    }

    void getPensioner() {
        if (Helper.getRandom(4) == 0) pensioneer = true;
    }

    @Override
    public void run() {
        enterToMarket();
        takeBasket();
        chooseGoods();
        putGoodsToBasket();
        goToOut();
    }

    @Override
    public void enterToMarket() {
        getPensioner();
        System.out.println(this + (pensioneer ? "пенсионер" : "") + "зашел в магазин");
    }

    @Override
    public void chooseGoods() {
        for (int i = 1; i <= Helper.getRandom(5); i++) {
            Helper.sleep(500, 2000, pensioneer);
            String goodName = Goods.rndGoodName();
            Double goodPrice = Goods.getPrice(goodName);
            System.out.println(this + "выбрал товар " + goodName + " цена:" + goodPrice);
        }
        System.out.println(this + "завершил выбор товаров");
    }

    @Override
    public void goToOut() {
        //todo all is bad (sync)
        Runner.queue.remove(this);
        System.out.println(this + "вышел из магазина");
        System.out.println("В очереди осталось " + Runner.queue.size());
    }

    @Override
    public String toString() {
        return this.getName() + " "; //"Покупатель №"+number
    }

    @Override
    public void takeBasket() {
        Helper.sleep(100, 200, pensioneer);
        System.out.println(this + "взял корзину");
    }

    @Override
    public void putGoodsToBasket() {
        Helper.sleep(100, 200, pensioneer);
        System.out.println(this + "положил выбранный товар в корзину");
    }
}
