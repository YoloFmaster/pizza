import kotlin.math.roundToInt

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

var allSoldCoffee = 0
val soldCoffee = mutableListOf(0,0,0,0)
const val costCoffee = 150.0
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
}

var countSellSauce = arrayListOf(0,0)
var allSeldSauce = 0
const val costBarbecueSauce = 50.0
const val costCheeseSauce = 30.0
interface Sauce{
    fun sellSauce(){
        println("\n1.Барбекью соус\n2.Сырный соус\n3.Не нужен соус")
        println("1. Да\n2. Нет")
        when(readln()){
            "1" ->{
                countSellSauce[0]++
                println("С вас ещё 50 рублей")
            }
            "2" ->{
                countSellSauce[1]++
                println("С вас ещё 30 рублей")
            }
            "3" ->{
                allSeldSauce--
            }
        }
        allSeldSauce++
    }
}

var countCheckPhoto = 0
const val discount = 50.0
interface Discount{
    fun checkPhoto(){
        println("\nУ вас есть чек с прошлой заказа у нас?")
        println("1. Да\n2. Нет")
        if (readln() == "1") {
            countCheckPhoto++
            println("Минус 50 рублей за весь ваш заказ")
        }
    }
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
        println("\nКоличество проднанной неаполитанской пиццы: $neapolitanPizzaCount")
        println("Количество проднанной римской пиццы: $romanPizzaCount")
        println("Количество проднанной сицильской пиццы: $sicilianPizzaCount")
        println("Количество проднанной тирольской пиццы: $tyroleanPizzaCount")

        var money = neapolitanPizzaCount * neapolitanPizzaPrice +
                romanPizzaCount * romanPizzaPrice +
                sicilianPizzaCount * sicilianPizzaPrice +
                tyroleanPizzaCount * tyroleanPizzaPrice
        val profitCoffee = allSoldCoffee * costCoffee
        money += profitCoffee
        val differnt = allSoldCoffee * 1.0 / countCustoms

        var maxI = 0
        var max = soldCoffee[0]
        for (i in soldCoffee.indices){
            if(max < soldCoffee[i]){
                max = soldCoffee[i]
                maxI = i
            }
        }
        val percentCoffee = (max * 10000.0 / allSoldCoffee).roundToInt() / 100.0
        when(maxI){
            0 -> println("Кофе больше покупаю к римской пицце: $max человек или такой процент людей ${percentCoffee}%")
            1 -> println("Кофе больше покупаю к неаполитанской пицце: $max человек или такой процент людей ${percentCoffee}%")
            2 -> println("Кофе больше покупаю к сцилийской пицце: $max человек или такой процент людей ${percentCoffee}%")
            3 -> println("Кофе больше покупаю к тирольской пицце: $max человек или такой процент людей ${percentCoffee}%")
        }

        println("Соотношение тех кто купил коффе на количесво заказов: ${(differnt * 10000).roundToInt() / 100.0}%")
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
        println("\nКоличество проднанной римской пиццы: $romanPizzaCount")
        println("Количество проднанной сицильской пиццы: $sicilianPizzaCount")
        println("Количество проднанной тирольской пиццы: $tyroleanPizzaCount")
        println("Количество проднанной неаполитанской пиццы: $neapolitanPizzaCount")

        var money = neapolitanPizzaCount * neapolitanPizzaPrice +
                romanPizzaCount * romanPizzaPrice +
                sicilianPizzaCount * sicilianPizzaPrice +
                tyroleanPizzaCount * tyroleanPizzaPrice
        val sumDiscount = countCheckPhoto * 50.0
        money -= sumDiscount
        val differntPhoto = countCheckPhoto * 1.0 / countCustoms
        println("Соотношение тех кто показал фото на количесво заказов: ${(differntPhoto * 10000).roundToInt() / 100.0}%")
        println("Общая сумма скидок: $sumDiscount")
        println("Прибыль за сегодня: $money")
    }
}

class PizzaRestaurantPskov(
    neapolitanPizzaPrice: Double,
    romanPizzaPrice: Double,
    sicilianPizzaPrice: Double,
    tyroleanPizzaPrice: Double,

    ) :PizzaCity(
    neapolitanPizzaPrice,
    romanPizzaPrice,
    sicilianPizzaPrice,
    tyroleanPizzaPrice
), Drink, Sauce, Discount {
    override fun romanPizzaSale() {
        println("")
        romanPizzaCount++
        typePizza = 0
        sellDrink(typePizza)
        sellSauce()
        checkPhoto()
    }

    override fun neapolitanPizzaSale() {
        println("")
        neapolitanPizzaCount++
        typePizza = 1
        sellDrink(typePizza)
        sellSauce()
        checkPhoto()
    }

    override fun sicilianPizzaSale() {
        println("")
        sicilianPizzaCount++
        typePizza = 2
        sellDrink(typePizza)
        sellSauce()
        checkPhoto()
    }

    override fun tyroleanPizzaSale() {
        println("")
        tyroleanPizzaCount++
        typePizza = 3
        sellDrink(typePizza)
        sellSauce()
        checkPhoto()
    }

    override fun showStatistic(countCustoms: Int) {
        println("\nКоличество проднанной римской пиццы: $romanPizzaCount")
        println("Количество проднанной неаполитанской пиццы: $neapolitanPizzaCount")
        println("Количество проднанной сицильской пиццы: $sicilianPizzaCount")
        println("Количество проднанной тирольской пиццы: $tyroleanPizzaCount")

        var money = neapolitanPizzaCount * neapolitanPizzaPrice +
                romanPizzaCount * romanPizzaPrice +
                sicilianPizzaCount * sicilianPizzaPrice +
                tyroleanPizzaCount * tyroleanPizzaPrice

        val profitCoffee = allSoldCoffee * costCoffee
        money += profitCoffee
        val differntCoffee = allSoldCoffee * 1.0 / countCustoms

        var maxI = 0
        var max = soldCoffee[0]
        for (i in soldCoffee.indices){
            if(max < soldCoffee[i]){
                max = soldCoffee[i]
                maxI = i
            }
        }
        val percentCoffee = (max * 10000.0 / allSoldCoffee).roundToInt() / 100.0
        when(maxI){
            0 -> println("Кофе больше покупаю к римской пицце: $max человек или такой процент людей ${percentCoffee}%")
            1 -> println("Кофе больше покупаю к неаполитанской пицце: $max человек или такой процент людей ${percentCoffee}%")
            2 -> println("Кофе больше покупаю к сцилийской пицце: $max человек или такой процент людей ${percentCoffee}%")
            3 -> println("Кофе больше покупаю к тирольской пицце: $max человек или такой процент людей ${percentCoffee}%")
        }

        val sumDiscount = countCheckPhoto * discount
        money -= sumDiscount
        val differntPhoto = countCheckPhoto * 1.0 / countCustoms

        val profitBarbecueSauce = countSellSauce[0] * costBarbecueSauce
        val profitCheeseSauce = countSellSauce[1] * costCheeseSauce

        money += profitBarbecueSauce + profitCheeseSauce

        println("Соотношение тех кто показал фото на количесво заказов: ${(differntPhoto * 10000).roundToInt() / 100.0}%")
        println("Соотношение тех кто купил кофе на количесво заказов: ${(differntCoffee * 10000).roundToInt() / 100.0}%")
        println("За сегодня было продано: $allSeldSauce")
        println("Общая прибыль за кофе: $profitCoffee")
        println("Общая прибыль за барбекю соус: $profitBarbecueSauce")
        println("Общая прибыль за сырный соус: $profitCheeseSauce")
        println("Общая сумма скидок: $sumDiscount")
        println("Прибыль за сегодня: $money")
    }
}