abstract class PizzaCity(
    val neapolitanPizzaPrice: Double,
    val romanPizzaPrice: Double,
    val sicilianPizzaPrice: Double,
    val tyroleanPizzaPrice: Double
) {
    var neapolitanPizzaCount = 0
    var romanPizzaCount = 0
    var sicilianPizzaCount = 0
    var tyroleanPizzaCount = 0
    var typePizza = 0

    abstract fun neapolitanPizzaSale()
    abstract fun romanPizzaSale()
    abstract fun sicilianPizzaSale()
    abstract fun tyroleanPizzaSale()
    abstract fun showStatistic(countCustoms: Int)
}

class PizzaRestaurantPeter(
    neapolitanPizzaPrice: Double,
    romanPizzaPrice: Double,
    sicilianPizzaPrice: Double,
    tyroleanPizzaPrice: Double,

) :PizzaCity(
    neapolitanPizzaPrice,
    romanPizzaPrice,
    sicilianPizzaPrice,
    tyroleanPizzaPrice
), Drink {
    override fun romanPizzaSale() {
        println("")
        romanPizzaCount++
        typePizza = 0
        sellDrink(typePizza)
    }

    override fun neapolitanPizzaSale() {
        println("")
        neapolitanPizzaCount++
        typePizza = 1
        sellDrink(typePizza)
    }

    override fun sicilianPizzaSale() {
        println("")
        sicilianPizzaCount++
        typePizza = 2
        sellDrink(typePizza)
    }

    override fun tyroleanPizzaSale() {
        println("")
        tyroleanPizzaCount++
        typePizza = 3
        sellDrink(typePizza)
    }

    override fun showStatistic(countCustoms: Int) {
        println("Количество проднанной неаполитанской пиццы: $neapolitanPizzaCount")
        println("Количество проднанной римской пиццы: $romanPizzaCount")
        println("Количество проднанной сицильской пиццы: $sicilianPizzaCount")
        println("Количество проднанной тирольской пиццы: $tyroleanPizzaCount")

        var money = neapolitanPizzaCount * neapolitanPizzaPrice +
                romanPizzaCount * romanPizzaPrice +
                sicilianPizzaCount * sicilianPizzaPrice +
                tyroleanPizzaCount * tyroleanPizzaPrice
        val profitCoffee = showStattisticDrink() * 150.0
        money += profitCoffee
        val differnt = allSoldCoffee * 1.0 / countCustoms

        var max = soldCoffee[0]
        for (i in soldCoffee.indices){
            if(max < soldCoffee[i]) max = soldCoffee[i]
        }
        when(max){
            0 -> println("Кофе больше покупаю к римской пицце ")
            1 -> println("Кофе больше покупаю к неаполитанской пицце")
            2 -> println("Кофе больше покупаю к сцилийской пицце")
            3 -> println("Кофе больше покупаю к тирольской пицце")
        }
        println("Соотношение тех кто купил коффе на количесво заказчико: ${differnt * 100}% или $differnt")
        println("Общая прибыль за кофе: $profitCoffee")
        println("Прибыль за сегодня: $money")
    }
}

class PizzaRestaurantMoscow(
    neapolitanPizzaPrice: Double,
    romanPizzaPrice: Double,
    sicilianPizzaPrice: Double,
    tyroleanPizzaPrice: Double
) :PizzaCity(
    neapolitanPizzaPrice,
    romanPizzaPrice,
    sicilianPizzaPrice,
    tyroleanPizzaPrice
), Discount {
    override fun romanPizzaSale() {
        romanPizzaCount++
        println("")
        checkPhoto()
    }

    override fun neapolitanPizzaSale() {
        println("")
        neapolitanPizzaCount++
        checkPhoto()
    }

    override fun sicilianPizzaSale() {
        println("")
        sicilianPizzaCount++
        checkPhoto()
    }

    override fun tyroleanPizzaSale() {
        println("")
        tyroleanPizzaCount++
        checkPhoto()
    }
    override fun showStatistic(countCustoms: Int) {
        println("Количество проднанной неаполитанской пиццы: $neapolitanPizzaCount")
        println("Количество проднанной римской пиццы: $romanPizzaCount")
        println("Количество проднанной сицильской пиццы: $sicilianPizzaCount")
        println("Количество проднанной тирольской пиццы: $tyroleanPizzaCount")

        var money = neapolitanPizzaCount * neapolitanPizzaPrice +
                romanPizzaCount * romanPizzaPrice +
                sicilianPizzaCount * sicilianPizzaPrice +
                tyroleanPizzaCount * tyroleanPizzaPrice
        val sumDiscount = showStatisticPhoto() * 50.0
        money -= sumDiscount
        val differnt = countCheckPhoto * 1.0 / countCustoms
        println("Соотношение тех кто купил коффе на количесво заказчико: ${differnt * 100}% или $differnt")
        println("Общая сумма скидок: $sumDiscount")
        println("Прибыль за сегодня: $money")
    }
}

var allSoldCoffee = 0
val soldCoffee = mutableListOf(0,0,0,0)
interface Drink{
    fun sellDrink(typePizza: Int) {
        println("Вы хотите взять кофе?")
        println("1. Да\n2. Нет")
        if (readln() == "1") {
            soldCoffee[typePizza]++
            allSoldCoffee++
            println("С вас ещё 150 рублей")
        }
    }
    fun showStattisticDrink(): Int {
        return allSoldCoffee
    }
}

interface Sauce{
    fun sellSauce()
}

var countCheckPhoto = 0
interface Discount{
    fun checkPhoto(){
        println("У вас есть чек с прошлой заказа у нас?")
        println("1. Да\n2. Нет")
        if (readln() == "1") {
            countCheckPhoto++
            println("Минус 50 рублей за весь ваш заказ")
        }
    }

    fun showStatisticPhoto(): Int{
        return countCheckPhoto
    }
}