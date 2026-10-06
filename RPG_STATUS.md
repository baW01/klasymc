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
