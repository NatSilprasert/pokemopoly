package com.pokemopoly.cards;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TypeEffectivenessTest {

    @Test
    void testFireSuperEffective() {
        List<PokemonType> result = TypeEffectiveness.getSuperEffective(PokemonType.FIRE);

        assertTrue(result.contains(PokemonType.GRASS));
        assertTrue(result.contains(PokemonType.ICE));
        assertTrue(result.contains(PokemonType.BUG));
        assertEquals(3, result.size());
    }

    @Test
    void testFireNotEffective() {
        List<PokemonType> result = TypeEffectiveness.getNotEffective(PokemonType.FIRE);

        assertTrue(result.contains(PokemonType.WATER));
        assertTrue(result.contains(PokemonType.ROCK));
        assertTrue(result.contains(PokemonType.FIRE));
        assertTrue(result.contains(PokemonType.DRAGON));
        assertEquals(4, result.size());
    }

    @Test
    void testElectricSuperEffective() {
        List<PokemonType> result = TypeEffectiveness.getSuperEffective(PokemonType.ELECTRIC);

        assertTrue(result.contains(PokemonType.WATER));
        assertTrue(result.contains(PokemonType.FLYING));
        assertEquals(2, result.size());
    }

    @Test
    void testElectricNotEffective() {
        List<PokemonType> result = TypeEffectiveness.getNotEffective(PokemonType.ELECTRIC);

        assertTrue(result.contains(PokemonType.GRASS));
        assertTrue(result.contains(PokemonType.ELECTRIC));
        assertTrue(result.contains(PokemonType.DRAGON));
        assertEquals(3, result.size());
    }

    @Test
    void testTypeWithNoSuperEffective() {
        // สมมุติว่า NORMAL ไม่มีใน superEffective map
        List<PokemonType> result = TypeEffectiveness.getSuperEffective(PokemonType.NORMAL);

        assertNotNull(result);
        assertTrue(result.isEmpty(), "NORMAL should return empty list for superEffective");
    }

    @Test
    void testTypeWithNoNotEffective() {
        // สมมุติว่า NORMAL ไม่มีใน notEffective map
        List<PokemonType> result = TypeEffectiveness.getNotEffective(PokemonType.NORMAL);

        assertNotNull(result);
        assertTrue(result.isEmpty(), "NORMAL should return empty list for notEffective");
    }

    @Test
    void testDragonSuperEffectiveSelf() {
        List<PokemonType> result = TypeEffectiveness.getSuperEffective(PokemonType.DRAGON);

        assertEquals(1, result.size());
        assertTrue(result.contains(PokemonType.DRAGON));
    }

    @Test
    void testGhostEffectiveness() {
        List<PokemonType> superEffective = TypeEffectiveness.getSuperEffective(PokemonType.GHOST);
        List<PokemonType> notEffective = TypeEffectiveness.getNotEffective(PokemonType.GHOST);

        assertTrue(superEffective.contains(PokemonType.GHOST));
        assertTrue(superEffective.contains(PokemonType.PSYCHIC));

        assertTrue(notEffective.contains(PokemonType.GHOST));
    }

}
