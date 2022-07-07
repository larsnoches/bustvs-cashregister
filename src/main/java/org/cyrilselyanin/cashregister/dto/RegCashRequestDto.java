package org.cyrilselyanin.cashregister.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
public class RegCashRequestDto {
    // обязательный, ID организации: 123
    Long companyID;

    // обязательный, ФИО кассира: Иванов И. И.
    String cashierFIO;

    /*
      обязательный, Тип операции:
            1 — «приход»
            2 — «возврат прихода»
      Например: 2
    */
    Integer operationType;

    // обязательный, Сумма наличными: 100
    BigDecimal cashSum;

    // обязательный, Сумма безналичными: Принимает значение из запроса
    BigDecimal bankSum;

    // обязательный, Сумма интернет-оплаты: Принимает значение из запроса
    BigDecimal internetSum;

    //обязательный,	Сумма оплаты на расчетный счет: Принимает значение из запроса
    BigDecimal accountSum;

    // обязательный, Сумма постоплаты (кредит): Принимает значение из запроса
    BigDecimal postpaySum;

    // обязательный, Сумма предоплаты (аванс): Принимает значение из запроса
    BigDecimal prepaySum;

    // обязательный, Сумма «БЕЗ НДС»: Принимает значение из запроса
    BigDecimal vatNone;

    // обязательный, Сумма «НДС 0%»: Принимает значение из запроса
    BigDecimal vatSum0;

    // обязательный, Сумма «НДС 10%»: Принимает значение из запроса
    BigDecimal vatSum10;

    // обязательный, Сумма «НДС 20%»: Принимает значение из запроса
    BigDecimal vatSum20;

    /* обязательный, Отгрузка/списание «в минус».
        Если в результате продаж товар уйдет в минус,
        ККТ перестанет регистрировать чеки, в которых есть этот товар:
        0 — запрещено, 1 — разрешено
    */
    Integer allowRetailPayed;

    // обязательный, Сумма «НДС 120%»: 100
    BigDecimal vatSum120;

    // обязательный, Наименование товарной позиции: интернет-товар
    String nameNomenclature;

    // обязательный, Штрихкод товарной позиции: 123456
    BigDecimal barcodeNomenclature;

    // обязательный, Цена товарной позиции: 100
    BigDecimal priceNomenclature;

    // обязательный, Количество: 1
    Integer quantityNomenclature;

    // обязательный, Единица измерения: шт (штука), кг (килограмм), л (литр). Например: шт
    String measureNomenclature;

    // обязательный, Признак предмета расчета: Т (товар), У (услуга). Например: т
    String kindNomenclature;

    // обязательный, Сумма предмета расчета: 100
    BigDecimal totalPriceNomenclature;

    // обязательный, Сумма НДС предмета расчета: 10
    BigDecimal taxRateNomenclature;

    // обязательный, Итого НДС: 100
    BigDecimal totalVat;

    // обязательный, ФИО покупателя: Принимает значение из запроса
    String customerFIO;

    // обязательный, Email покупателя: Принимает значение из запроса
    String customerEmail;

    // обязательный, Номер телефона покупателя: Принимает значение из запроса
    Integer customerPhone;

    // обязательный, ИНН покупателя: Принимает значение из запроса
    String customerINN;

    // обязательный, Внешний ИД покупателя: Принимает значение из запроса
    Long customerExtId;

    /* обязательный, Тип системы налогообложения (СНО)
        1 - общая,
        2 - упрощенная (доход),
        4 - упрощенная (доход минус расход),
        8 - ЕВНД,
        16 - ЕСХН,
        32 - патент.
        Например: 1
    */
    Integer taxSystem;

    // обязательный, Адрес электронной почты покупателя для отправки чека: test@test.ru
    String sendEmail;

    // обязательный, Номер телефона покупателя для отправки чека: Принимает значение из запроса
    Integer sendPhone;

    // обязательный, Наименование доп. реквизита: Принимает значение из запроса
    String propName;

    // обязательный, Значение доп. реквизита: Принимает значение из запроса
    String propVa;

    // обязательный, Комментарий к чеку, в ОФД не передается: Тестовый чек
    String comment;

    /* обязательный, Признак способа расчета:
        1 - предоплата 100%,
        2 - предоплата,
        3 - аванс,
        4 - полный расчет,
        5 - частичный расчет и кредит,
        6 - передача в кредит,
        7 - оплата кредита.
        Например: 4
    */
    Integer payMethod;

    // обязательный, Уникальный идентификатор платежа: 4b3d1874-b47c-11ea
    String externalId;
}
