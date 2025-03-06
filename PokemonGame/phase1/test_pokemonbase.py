"""
test_pokemonbase.py: Testing pokemonbase.py file
"""
import unittest
from pokemon import Charmander, MissingNo
from tester_base import TesterBase

__author__ = "Ravindi Ratnayake"
class TestPokemonBase(TesterBase):

    def test_set_hp(self):
        p1 = Charmander()

        try:  # Try calling PokemonBase set_hp method
            p1.set_hp(4)
        except Exception as e:
            self.verificationErrors.append("Failure in set_hp method: {}".format(str(e)))
            return
        try:  # Testing if set_hp method is implemented correctly
            assert p1.get_hp() == 4, "HP should be 4"
        except Exception as e:
            self.verificationErrors.append("Error in set_hp method: {}".format(str(e)))

    def test_has_fainted(self):
        p1 = Charmander()

        try:    # Try calling PokemonBase has_fainted method
            p1.has_fainted()
        except Exception as e:
            self.verificationErrors.append("Failure in is_fainted method".format(str(e)))
            return
        try:  # Testing if has_fainted method is implemented correctly
            assert p1.has_fainted() == False, "Pokemon should not be fainted"
        except Exception as e:
            self.verificationErrors.append("Error in is_fainted method: {}".format(str(e)))

    def test_set_level(self):
        p1 = Charmander()

        try:  # Try calling PokemonBase set_level method
            p1.set_level(6)
        except Exception as e:
            self.verificationErrors.append("Failure in set_level method: {}".format(str(e)))
            return
        try:  # Testing if get_level method is implemented correctly
            assert p1.get_level() == 6, "Level should be 6"
        except Exception as e:
            self.verificationErrors.append("Error in set_level method: {}".format(str(e)))

    def test_get_hp(self):
        p1 = Charmander()

        try:  # Try calling PokemonBase get_hp method
            p1.get_hp()
        except Exception as e:
            self.verificationErrors.append("Failure in get_hp method: {}".format(str(e)))
            return
        try:  # Testing if get_hp method is implemented correctly
            assert p1.get_hp() == 7
        except Exception as e:
            self.verificationErrors.append("Error in get_hp method: {}".format(str(e)))

    def test_get_level(self):
        p1 = Charmander()

        try:  # Try calling PokemonBase get_level method
            p1.get_level()
        except Exception as e:
            self.verificationErrors.append("Failure in get_level method: {}".format(str(e)))
            return
        p1.set_level(4)
        try:  # Testing if get_level method is implemented correctly
            assert p1.get_level() == 4, "Level should be 4"
        except Exception as e:
            self.verificationErrors.append("Error in get_level method: {}".format(str(e)))

    def test_get_poke_type(self):
        p1 = Charmander()

        try:  # Try calling PokemonBase get_poke_type method
            p1.get_poke_type()
        except Exception as e:
            self.verificationErrors.append("Failure in get_poke_type method: {}".format(str(e)))
            return

        try:  # Testing if get_poke_type method is implemented correctly
            assert p1.get_poke_type() == "Fire", "Poke type should be Fire"
        except Exception as e:
            self.verificationErrors.append("Error in get_poke_type method: {}".format(str(e)))

    def test_up_hp(self):
        p2 = MissingNo()

        try:  # Try calling GlitchMon up_hp method
            p2.up_hp(1)
        except Exception as e:
            self.verificationErrors.append("Failure in up_hp method: {}".format(str(e)))
            return
        try:  # Testing if up_hp method is implemented correctly
            assert p2.get_hp() == 9, "HP should be 9"
        except Exception as e:
            self.verificationErrors.append("Error in up_hp method: {}".format(str(e)))

    def test_superpower(self):
        p2 = MissingNo()

        try:  # Try calling GlitchMon superpower method
            p2.superpower()
        except Exception as e:
            self.verificationErrors.append("Failure in superpower() method: {}".format(str(e)))
            return

        try:  # Testing if superpower method is implemented correctly
            assert (p2.get_hp() == 9) or (
                        p2.get_level() == 2), "Level must increase to 2 or HP must increase to 9, or both must increase."
        except Exception as e:
            self.verificationErrors.append("Error in superpower() method: {}".format(str(e)))


if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestPokemonBase)
    unittest.TextTestRunner(verbosity=0).run(suite)