package com.academy.brawler;

import com.academy.brawler.game.Attributes;
import com.academy.brawler.game.Items.Types.Weapons.CreateWeapon;
import com.academy.brawler.game.Items.Types.Weapons.Weapon;
import com.academy.brawler.game.Items.Types.Weapons.WeaponType;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.io.InvalidObjectException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateWeaponTest extends TestCase {
    final static int NEGATIVE_VALUE = -1;
    final static int ZERO_VALUE = 0;
    final static int LOW_VALUE = 1;
    final static int MEDIUM_VALUE = 3;
    final static int HIGH_VALUE = 5;
    final static Attributes VALID_ATTRIBUTES = new Attributes().dagger(20);
    final static Attributes INVALID_ATTRIBUTES = new Attributes().dagger(-20);


    @Test
    public void testWeaponTypes() throws InvalidObjectException {

        for (WeaponType weaponType : WeaponType.values()) {
            if (weaponType.isOneHanded()) {
                Weapon oneHand = testWeaponType(weaponType, false);
                assertNotNull(oneHand);
            }
            if (weaponType.isTwoHanded()) {
                Weapon twoHand = testWeaponType(weaponType, true);
                assertNotNull(twoHand);
            }
        }
    }

    public Weapon testWeaponType(final WeaponType weaponType, final boolean isTwoHanded) throws InvalidObjectException {
        String weaponName = weaponType.name();
        String weaponDescription = weaponName + " description";

        CreateWeapon createWeapon = new CreateWeapon();
        createWeapon.generate(weaponType, weaponName, weaponDescription, isTwoHanded);
        assertNotNull(createWeapon);

        //Checking for negative values
        assertThrows(IllegalArgumentException.class, () -> createWeapon.damage(NEGATIVE_VALUE, ZERO_VALUE, ZERO_VALUE));
        assertThrows(IllegalArgumentException.class, () -> createWeapon.damage(ZERO_VALUE, NEGATIVE_VALUE, ZERO_VALUE));
        assertThrows(IllegalArgumentException.class, () -> createWeapon.damage(ZERO_VALUE, ZERO_VALUE, NEGATIVE_VALUE));
        //Check max damage higher than damage ceiling
        assertThrows(IllegalArgumentException.class, () -> createWeapon.damage(LOW_VALUE, HIGH_VALUE, MEDIUM_VALUE));
        //Check min damage higher than max damage
        assertThrows(IllegalArgumentException.class, () -> createWeapon.damage(MEDIUM_VALUE, LOW_VALUE, HIGH_VALUE));

        createWeapon.damage(LOW_VALUE, MEDIUM_VALUE, HIGH_VALUE);

        assertThrows(IllegalArgumentException.class, () -> createWeapon.breakValue(NEGATIVE_VALUE));
        createWeapon.breakValue(LOW_VALUE);

        assertThrows(IllegalArgumentException.class, () -> createWeapon.weight(NEGATIVE_VALUE));
        createWeapon.weight(LOW_VALUE);

        assertThrows(IllegalArgumentException.class, () -> createWeapon.requirements(INVALID_ATTRIBUTES));
        createWeapon.requirements(VALID_ATTRIBUTES);

        assertThrows(IllegalArgumentException.class, () -> createWeapon.attributes(INVALID_ATTRIBUTES));
        createWeapon.attributes(VALID_ATTRIBUTES);

        return createWeapon.build();
    }
}