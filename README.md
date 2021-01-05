Téma práce: Smart home

Funkční požadavky:
F1: Máme dům, žaluzie, místo pater máme pokoje, sledujeme počasí, spotřebiče a osoby máme, auto ne, kolo a domácí zvířata máme.
F2: Zařízení lze zapínat, sportovní vybavení používat, obojí za předpokladu, že zrovna nejsou rozbité (/právě používané v případě kol a lyží). Lednička a pračka mají obsah.
F3: Spotřebiče mají svou spotřebu v on a off stavu.
F4: Spotřebiče si počítají svoji spotřebu, ta se pak reportuje
F5: Osoby používají spotřebiče, osoby, zvířata a spotřebiče vznášejí požadavky na osoby.
F6: Osoby se pohybují po domě a venku sportují, zvířata se pohybují uvnitř i venku, spotřebiče jsou uvnitř. Co není v místnosti „outside“ je v nějaké místnosti uvnitř.
F7: Každá osoba má svůj seznam úkolů (request). Pokud úkol splnit neumí (nebo když jich mají rodiče moc) předá úkol jiné osobě. 
F8: ano
F9: Oprava zabere dva tiky – dvě hodiny, ale řeší se jen funkcí repair.
F10: Když osobě dojdou úkoly, sportuje nebo se dívá na televizi.

Nefunkční požadavky:
Konfigurace domu se načítá z jsonu.
Report se ukládá do souboru report.txt
Je jen jedno vlákno, každou hodinu symbolizuje jeden tik

Design patterny:
State machine – ve třídách Clothes a Foodstuff
Factory – ve třídě ApplianceFactory
Composite – třídy s názvy „...Fasda“ plní spíše funkci Composite než fasády. Spolu s třídou House v kořeni tvoří vnitřní uzly stromu, po němž se lze dostat do (téměř) kterékoliv třídy.
Singleton – třídy Mother a Father. Singleton zde má spíše symbolickou roli, simulace by měla proběhnout i s více instancemi.
Visitor – Reporter navštěvuje třídy implementující Tickable (Z důvodů ušetření kódu podobně fungují i metody jako use(Person person) ve třídě Ski.) 
Observer – Time a Person, Weather a Blinds


