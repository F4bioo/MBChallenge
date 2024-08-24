package com.fappslab.mbchallenge.features.exchanges.presentation.compose.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fappslab.mbchallenge.libraries.design.theme.PlutoTheme

@Composable
internal fun ExchangeCircularLoadingIndicator(
    modifier: Modifier = Modifier,
    shouldShow: Boolean
) {
    if (shouldShow) {
        CircularProgressIndicator(
            modifier = modifier.size(PlutoTheme.dimen.dp24),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExchangeCircularLoadingIndicatorPreview() {
    ExchangeCircularLoadingIndicator(
        shouldShow = true
    )
}
