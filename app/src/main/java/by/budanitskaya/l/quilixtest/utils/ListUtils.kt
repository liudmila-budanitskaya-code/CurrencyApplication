package by.budanitskaya.l.quilixtest.utils

import by.budanitskaya.l.quilixtest.data.network.models.RemoteCurrencyInfo
import by.budanitskaya.l.quilixtest.presentation.models.CurrencyPresentationModel


fun makePresentationList(
    currentDayList: List<RemoteCurrencyInfo>,
    nextDayList: List<RemoteCurrencyInfo>
): List<CurrencyPresentationModel> {
    return currentDayList.zip(nextDayList) { currentDayData, nextDayData ->
        CurrencyPresentationModel(
            currentDayData.charCode ?: "123",
            "${currentDayData.scale} ${currentDayData.name}",
            nextDayData.rate,
            currentDayData.rate
        )
    }
}