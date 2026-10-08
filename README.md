# klasymc

## Soulbound RPG — założenia systemu

Ten dokument opisuje docelowy kierunek rozwoju serwera: klasy, buildy, rozwój postaci przez styl gry, reakcje między skillami i modularną architekturę pluginów.

## 1) Fundament: żyjąca postać (bez sztywnego wyboru specjalizacji)

Gracz wybiera klasę bazową, ale specjalizacja rozwija się dynamicznie na podstawie:
- używanych umiejętności,
- typu zadawanych obrażeń i statusów,
- używanego ekwipunku,
- podejmowanych decyzji w walce.

### Klasy główne
- **WARRIOR**: rage/stamina, block/parry, charge, taunt, bleed
- **ROGUE**: energia, combo points, stealth, backstab, poison, dash, dodge
- **MAGE**: mana, cast time, żywioły, teleport, tarcze magiczne, freeze/burn/shock

### Specjalizacje (docelowo)
- Warrior: Berserker, Guardian, Blood Knight
- Rogue: Assassin, Shadowblade, Ranger
- Mage: Pyromancer, Cryomancer, Arcane Mage

### Dynamiczne odblokowanie specjalizacji
Zamiast komendy typu `/class pyromancer`, postać gromadzi **Affinity** (np. Fire Affinity), które rośnie wraz z grą. Po przekroczeniu progów odblokowywane są nowe skille i pasywki odpowiedniej specjalizacji.

## 2) Skill Tree i hybrydy

Każda klasa ma duże drzewko umiejętności z gałęziami i węzłami hybrydowymi.

Przykład dla maga:
- Arcane
  - Fire
    - Fireball I → Fireball II → Inferno
  - Frost
    - Ice Bolt I → Frost Nova → Ice Prison
  - Hybrid nodes:
    - Fire + Arcane → Arcane Explosion
    - Fire + Frost → Thermal Shock

## 3) Reakcje między skillami i Combat Tags

System oparty o statusy (`Combat Tags`) i reakcje między klasami.

### Statusy
`BURNING`, `FROZEN`, `WET`, `POISONED`, `BLEEDING`, `STUNNED`, `SHOCKED`, `CURSED`, `WEAKENED`, `VULNERABLE`

### Przykładowe reakcje
- `FROZEN + Heavy Attack` → `SHATTER` (wysoki bonus obrażeń)
- `POISONED + Fire` → `TOXIC_EXPLOSION` (AoE)
- `WET + Lightning` → bonus damage + chain targets

Reakcje są centralnym elementem współpracy w party.

## 4) Resource & Combo systems

Każda klasa ma własny gameplay loop:
- Warrior: Rage/Stamina
- Rogue: Combo Points (finisher skaluje się z CP)
- Mage: Mana / Arcane Charges

## 5) Przedmioty, rarity i procedural generation

### Rarity
`COMMON`, `UNCOMMON`, `RARE`, `EPIC`, `LEGENDARY`, `MYTHIC`, `RELIC`, `CORRUPTED`

### Corrupted items
Przedmioty z wysokim bonusem i kosztem (np. większy damage kosztem max HP).

### Procedural item system
Generator nazw i statów:
- `[Prefix] [Item] [Suffix]`
- np. `Ancient Blade of Fury`

## 6) Boss AI i aggro

### Boss phases
Bossy mają fazy zależne od HP (np. 100–70%, 70–30%, <30%) z osobnymi zachowaniami i skillami.

### Threat/Aggro
Każdy gracz generuje threat; boss wybiera cel na podstawie aggro. Taunt i narzędzia tanka muszą mieć realny wpływ na tabelę threat.

## 7) Soul System, Relics, Skill Mutation

### Boss Souls
Boss może dropić Soul, którą można:
- sprzedać,
- zużyć do craftingu,
- wchłonąć dla unikalnej umiejętności bossa.

Ograniczenie: do 3 aktywnych Soul jednocześnie.

### Relic slots
Postać ma sloty reliktów modyfikujące działanie skilli.

### Skill Mutation
Każdy skill może mieć mutacje (np. więcej pocisków kosztem obrażeń, większe AoE kosztem cast time, tracking projectile).

## 8) Statystyki

Core stats:
- Strength, Dexterity, Intelligence, Vitality, Spirit

Combat stats:
- Critical Chance, Critical Damage, Attack Speed, Cooldown Reduction
- Armor, Magic Resistance
- Fire/Frost/Poison Damage
- Life Steal, Mana Regen

## 9) Level/Rank mobów

Każdy mob ma:
- **Level**
- **Tier/Rank** (`NORMAL`, `ELITE`, `CHAMPION`, `BOSS`)

Rank wpływa na AI, mechaniki i loot.

## 10) Architektura pluginów (modułowa)

Docelowa architektura:

```text
RPG-Core
|
├── RPG-Characters
├── RPG-Classes
├── RPG-Skills
├── RPG-Combat
├── RPG-Items
├── RPG-Mobs
├── RPG-Bosses
├── RPG-Quests
├── RPG-Dungeons
├── RPG-Parties
├── RPG-Guilds
└── RPG-Economy
```

`RPG-Core` udostępnia API do wspólnego modelu gracza i systemów.

Przykład API:

```java
RPGPlayer player = RPG.getPlayer(uuid);

player.getClazz();
player.getStats();
player.getSkills();
player.getEquipment();
```

## 11) MVP startowe (pierwszy plugin: RPGCombatEngine)

Aby dostarczyć działającą bazę, pierwszy etap obejmuje:
1. klasy,
2. statystyki,
3. resource system (mana/rage/energy),
4. skill engine,
5. cooldowny,
6. status effects,
7. damage calculation,
8. combo system,
9. custom mobs API,
10. boss phases.

### Zakres na premierę
- 3 klasy × 2 specjalizacje:
  - Warrior: Guardian, Berserker
  - Mage: Pyromancer, Cryomancer
  - Rogue: Assassin, Shadowblade
- ~8–10 aktywnych skilli na klasę + pasywki

## 12) Przykładowa konfiguracja skilla

```yaml
fireball:
  class: MAGE
  level: 4

  resource:
    type: MANA
    cost: 35

  cooldown: 6

  cast:
    type: PROJECTILE
    speed: 1.8

  damage:
    base: 70
    scaling:
      intelligence: 1.4

  effects:
    - type: BURN
      duration: 5
      damage: 10

  reactions:
    WET:
      damage_multiplier: 1.5

    FROZEN:
      effect: THERMAL_SHOCK
      bonus_damage: 100
```
