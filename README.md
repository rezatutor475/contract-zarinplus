ZarinPlus Contract
========================

A Contract for communicate to ZarinPal SDK.

Usage
-----

1- Import `ZarinplusContract` as a `Contract` in your project package as class 

2- Implement `Contract` Package by `Gradle`:

```gradle
implementation("com.zarinpal:contract:[VERSION]")
```

3- Invoke `ZarinplusContract.create(token, title, description)` to create instance
```kotlin

   Contract contract = ZarinPlusContract.create(
         [AUTHORIZATION],
                    "زرین‌پلاس",
                    "پرداخت اعتباری زرین‌پلاس");
```

4- set instance of contract by `setContract` method via `ZarinPalBillingClient`


```kotlin

 val client = ZarinPalBillingClient.newBuilder(this)
            .enableShowInvoice()
            .setContract(contract)
            ..
            ....
            .build()
```


License
=======

    Copyright 2023 Alireza Tarazani - ImanX

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

