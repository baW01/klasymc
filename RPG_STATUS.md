# RPGCombatEngine — status funkcji

Ten plik jest pojedynczym miejscem do śledzenia postępu:
- **GOTOWE**
- **WYMAGA POPRAWEK**
- **NIEZROBIONE**

## GOTOWE

- [x] Opis architektury modułowej (`RPG-Core`, moduły domenowe) w `README.md`.
- [x] Definicja docelowego zakresu MVP (`RPGCombatEngine`) w `README.md`.
- [x] Opis klas bazowych i specjalizacji.
- [x] Opis systemu statusów (`Combat Tags`) i reakcji między skillami.
- [x] Przykładowa konfiguracja skilla (`fireball`) z kosztami, cooldownem, efektami i reakcjami.

## WYMAGA POPRAWEK

- [ ] Ujednolicenie słownictwa technicznego (PL/EN) dla nazw systemowych (np. `threat`, `aggro`, `affinity`).
- [ ] Doprecyzowanie progów odblokowania specjalizacji (konkretne wartości i warunki).
- [ ] Doprecyzowanie zasad balansu reakcji statusów (np. mnożniki obrażeń i limity efektów łańcuchowych).
- [ ] Doprecyzowanie zasad proceduralnego generatora itemów (wagi prefiksów/sufiksów i ograniczenia rolli).
- [ ] Ustalenie jednoznacznych definicji `Tier` i `Rank` dla mobów (żeby uniknąć rozjazdu pojęć).

## NIEZROBIONE

### Core i model postaci
- [ ] Model `RPGPlayer` (klasa, statystyki, zasoby, eq, relikty, soul slots).
- [ ] System progresji postaci i doświadczenia.
- [ ] System Affinity i dynamicznego odblokowania specjalizacji.

### Skill engine
- [ ] Rejestr skilli i walidacja konfiguracji.
- [ ] Mechanika castowania (projectile, instant, channel, AoE).
- [ ] System cooldownów globalnych i per-skill.
- [ ] Skill Mutation (warianty działania skilli).

### Combat i statusy
- [ ] Silnik obrażeń ze skalowaniem statystyk.
- [ ] Nakładanie i odświeżanie statusów (`BURNING`, `FROZEN`, `WET`, itd.).
- [ ] Silnik reakcji między statusami i typami ataków (`SHATTER`, `TOXIC_EXPLOSION`, `THERMAL_SHOCK`).
- [ ] Obsługa zasobów klas (`MANA`, `RAGE`, `ENERGY`).
- [ ] System combo pointów i finisherów dla Rogue.

### Moby i bossy
- [ ] API custom mobów (level/tier/rank).
- [ ] Boss phase engine (progi HP, zmiana zachowań i skilli).
- [ ] Threat/Aggro table i `Taunt`.

### Itemy i buildy
- [ ] Runtime dla rarity (`COMMON` → `CORRUPTED`) i zasad rolli.
- [ ] Proceduralny generator itemów (`[Prefix] [Item] [Suffix]`).
- [ ] System unikalnych efektów broni.
- [ ] Soul System (drop, crafting, absorb, limit aktywnych soul).
- [ ] Relic system (sloty i modyfikacje skilli).

### Jakość i testy
- [ ] Szkielet projektu z buildem i testami automatycznymi.
- [ ] Testy jednostkowe silnika obrażeń, statusów i reakcji.
- [ ] Testy integracyjne przepływów klas/specjalizacji.

## KRYTERIA GOTOWOŚCI MVP (DO WDROŻENIA)

### Minimalny gameplay loop
- [ ] Gracz wybiera klasę bazową i ma działające zasoby klasy (`MANA`/`RAGE`/`ENERGY`).
- [ ] Co najmniej 1 build na klasę ma pełną pętlę: obrażenia, statusy, cooldowny, reakcje.
- [ ] Co najmniej 1 boss działa w 3 fazach z prostym aggro.

### Minimalna konfiguracja danych
- [ ] Skille klas MVP są ładowane z konfiguracji i walidowane przy starcie.
- [ ] Statusy i reakcje są konfigurowalne bez zmiany kodu.
- [ ] Co najmniej 10 przykładowych itemów (w tym 2 z unikalnym efektem) jest zdefiniowanych konfiguracyjnie.

### Minimalna jakość techniczna
- [ ] Pokryte testami: damage formula, status application, reaction trigger.
- [ ] Pokryte testami: cooldown i resource spending.
- [ ] Pokryte testami: podstawowy flow bossa (phase switch po progach HP).

## NAJBLIŻSZE PRIORYTETY (KOLEJNOŚĆ REALIZACJI)

1. [ ] **Combat Core v1**: damage + statusy + reakcje (fundament całego systemu).
2. [ ] **Skill Engine v1**: cast, cooldown, koszt zasobu, walidacja configu.
3. [ ] **Class Resources v1**: MANA/RAGE/ENERGY + combo points Rogue.
4. [ ] **Boss Phase v1**: progi HP, zestawy zachowań, proste aggro i taunt.
5. [ ] **Item Runtime v1**: rarity, podstawowe statystyki i pierwsze unique efekty.
6. [ ] **Test Harness v1**: testy jednostkowe dla modułów core.
