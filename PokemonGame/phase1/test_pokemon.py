"""
test_pokemon.py: Testing pokemon.py file
"""
import unittest
from tester_base import TesterBase, captured_output

__author__ = "Mah Ying Qi"


class TestPokemon(TesterBase):
    def test_Charmander_init(self):
        from pokemon import Charmander
        try:  # Testing creating Charmander object
            charmander = Charmander()
        except Exception as e:
            self.verificationErrors.append(f"Charmander cannot be created: {str(e)}")
            return

        try:  # Testing if charmander object is created correctly
            assert charmander.hp == 7, "Incorrect hp"
            assert charmander.poke_type == "Fire", "Incorrect poke_type"
            assert charmander.attack == 7, "Incorrect attack"
            assert charmander.speed == 8, "Incorrect speed"
            assert charmander.defence == 4, "Incorrect defence"
        except Exception as e:
            self.verificationErrors.append(f"Charmander is not created correctly: {str(e)}")

    def test_Bulbasaur_init(self):
        from pokemon import Bulbasaur
        try:  # Testing creation of Bulbasaur
            bulbasaur = Bulbasaur()
        except Exception as e:
            self.verificationErrors.append(f"Bulbasaur cannot be created: {str(e)}")
            return

        try:  # Testing if Bulbasaur object is created correctly
            assert bulbasaur.hp == 9, "Incorrect hp"
            assert bulbasaur.poke_type == "Grass", "Incorrect poke_type"
            assert bulbasaur.attack == 5, "Incorrect attack"
            assert bulbasaur.speed == 7, "Incorrect speed"
            assert bulbasaur.defence == 5, "Incorrect defence"
        except Exception as e:
            self.verificationErrors.append(f"Bulbasaur is not created correctly: {str(e)}")

    def test_Squirtle_init(self):
        from pokemon import Squirtle
        try:  # Testing creation of squirtle object
            squirtle = Squirtle()
        except Exception as e:
            self.verificationErrors.append(f"Squirtle cannot be created: {str(e)}")
            return

        try:  # Testing if squirtle object is created correctly
            assert squirtle.hp == 8, "Incorrect hp"
            assert squirtle.poke_type == "Water", "Incorrect poke_type"
            assert squirtle.attack == 4, "Incorrect attack"
            assert squirtle.speed == 7, "Incorrect speed"
            assert squirtle.defence == 7, "Incorrect defence"
        except Exception as e:
            self.verificationErrors.append(f"Squirtle is not created correctly: {str(e)}")

    def test_MissingNo_init(self):
        from pokemon import MissingNo
        try:  # Testing Creation of MissingNo object
            missingno = MissingNo()
        except Exception as e:
            self.verificationErrors.append(f"MissingNo cannot be created: {str(e)}")
            return

        try:  # Testing if MissingNo object is created correctly
            assert missingno.hp == 8, "Incorrect hp"
            assert missingno.poke_type == "None", "Incorrect poke_type"
            assert missingno.attack == 6, "Incorrect attack"
            assert missingno.speed == 7, "Incorrect speed"
            assert missingno.defence == 6, "Incorrect defence"
        except Exception as e:
            self.verificationErrors.append(f"MissingNo is not created correctly: {str(e)}")

    def test_Charmander_get_attack(self):
        from pokemon import Charmander
        charmander = Charmander()
        try:  # Testing calling of charmander get_attack method
            x = charmander.get_attack()
        except Exception as e:
            self.verificationErrors.append(f"Charmander get attack method failed: {str(e)}")
            return

        try:  # Testing if charmander get_attack method returns the correct value
            assert x == 7, "Attack should be 7"
        except AssertionError:
            self.verificationErrors.append(f"Charmander get attack method is incorrect: {str(x)}")

    def test_Charmander_get_defence(self):
        from pokemon import Charmander
        charmander = Charmander()
        try:  # Testing calling of charmander get_defence method
            x = charmander.get_defence()
        except Exception as e:
            self.verificationErrors.append(f"Charmander get defence method failed: {str(e)}")
            return

        try:  # Testing if charmander get_defence method returns the correct value
            assert x == 4, "defence should be 4"
        except AssertionError:
            self.verificationErrors.append(f"Charmander get defence method is incorrect: {str(x)}")

    def test_Charmander_get_speed(self):
        from pokemon import Charmander
        charmander = Charmander()
        try:  # Testing calling of charmander get_speed method
            x = charmander.get_speed()
        except Exception as e:
            self.verificationErrors.append(f"Charmander get speed method failed: {str(e)}")
            return

        try:  # Testing if charmander get_speed method returns the correct value
            assert x == 8, "Speed should be 7"
        except AssertionError:
            self.verificationErrors.append(f"Charmander get speed method is incorrect: {str(x)}")

    def test_Charmander_get_name(self):
        from pokemon import Charmander
        charmander = Charmander()
        try:  # Testing calling of charmander get_name method
            x = charmander.get_name()
        except Exception as e:
            self.verificationErrors.append(f"Charmander get name method failed: {str(e)}")
            return

        try:  # Testing if charmander get_name method returns the correct value
            assert x == "Charmander", "Name should be Charmander"
        except AssertionError:
            self.verificationErrors.append(f"Charmander get name method is incorrect: {str(x)}")

    def test_Charmander_set_level(self):
        from pokemon import Charmander
        charmander = Charmander()
        try:  # Testing calling of charmander set_level method
            charmander.set_level(2)
        except Exception as e:
            self.verificationErrors.append(f"Charmander set level method failed: {str(e)}")
            return

        try:  # Testing if charmander set_level method is implemented correctly
            assert charmander.get_level() == 2, "Level should be 2"
            assert charmander.get_speed() == 9, "Speed should be 9"
            assert charmander.get_attack() == 8, "Speed should be 8"
        except Exception as x:
            self.verificationErrors.append(f"Charmander set level method is incorrect: {str(x)}")

    def test_Charmander_calculate_damage_dealt(self):
        from pokemon import Charmander
        x = Charmander()  # Creating an enemy for testing
        y = Charmander()
        try:  # Testing calling of charmander calculate_damage_dealt method
            result = x.calculate_damage_dealt(y)
        except Exception as e:
            self.verificationErrors.append(f"Charmander calculate damage dealt method failed: {str(e)}")
            return

        try:  # Testing if charmander calculate_damage_dealt method returns the correct value
            assert result == 7, "Damage dealt should be 7"
        except Exception as x:
            self.verificationErrors.append(f"Charmander calculate damage dealt method is incorrect: {str(x)}")

    def test_Charmander_str(self):
        from pokemon import Charmander
        charmander = Charmander()
        try:  # Testing calling of charmander str method
            x = str(charmander)
        except Exception as e:
            self.verificationErrors.append(f"Charmander str method failed: {str(e)}")
            return

        try:  # Testing if charmander str method returns the correct value
            assert x == "Charmander's HP = 7 and level = 1"
        except AssertionError:
            self.verificationErrors.append(f"Charmander str method is incorrect: {x}")

    def test_Bulbasaur_get_attack(self):
        from pokemon import Bulbasaur
        bulbasaur = Bulbasaur()
        try:  # Testing calling of bulbasaur get_attack method
            x = bulbasaur.get_attack()
        except Exception as e:
            self.verificationErrors.append(f"Bulbasaur get attack method failed: {str(e)}")
            return

        try:  # Testing if bulbasaur get_attack method returns the correct value
            assert x == 5, "Attack should be 5"
        except AssertionError:
            self.verificationErrors.append(f"Bulbasaur get attack method is incorrect: {str(x)}")

    def test_Bulbasaur_get_defence(self):
        from pokemon import Bulbasaur
        bulbasaur = Bulbasaur()
        try:  # Testing calling of Bulbasaur get_defence method
            x = bulbasaur.get_defence()
        except Exception as e:
            self.verificationErrors.append(f"Bulbasaur get defence method failed: {str(e)}")
            return

        try:  # Testing if bulbasaur get_defence method returns the correct value
            assert x == 5, "defence should be 5"
        except AssertionError:
            self.verificationErrors.append(f"Bulbasaur get defence method is incorrect: {str(x)}")

    def test_Bulbasaur_get_speed(self):
        from pokemon import Bulbasaur
        bulbasaur = Bulbasaur()
        try:  # Testing calling of Bulbasaur get_speed method
            x = bulbasaur.get_speed()
        except Exception as e:
            self.verificationErrors.append(f"Bulbasaur get speed method failed: {str(e)}")
            return

        try:  # Testing if bulbasaur get_speed method returns the correct value
            assert x == 7, "Speed should be 7"
        except AssertionError:
            self.verificationErrors.append(f"Bulbasaur get speed method is incorrect: {str(x)}")

    def test_Bulbasaur_get_name(self):
        from pokemon import Bulbasaur
        bulbasaur = Bulbasaur()
        try:  # Testing calling of Bulbasaur get_name method
            x = bulbasaur.get_name()
        except Exception as e:
            self.verificationErrors.append(f"Bulbasaur get name method failed: {str(e)}")
            return

        try:  # Testing if bulbasaur get_name method returns the correct value
            assert x == "Bulbasaur", "Name should be Bulbasaur"
        except AssertionError:
            self.verificationErrors.append(f"Bulbasaur get name method is incorrect: {str(x)}")

    def test_Bulbasaur_set_level(self):
        from pokemon import Bulbasaur
        x = Bulbasaur()
        try:  # Testing calling of Bulbasaur set_level method
            x.set_level(2)
        except Exception as e:
            self.verificationErrors.append(f"Bulbasaur set level method failed: {str(e)}")
            return

        try:  # Testing if bulbasaur set_level method is implemented correctly
            assert x.get_level() == 2, "Level should be 2"
            assert x.get_speed() == 8, "Speed should be 8"
        except Exception as x:
            self.verificationErrors.append(f"Bulbasaur set level method is incorrect: {str(x)}")

    def test_Bulbasaur_calculate_damage_dealt(self):
        from pokemon import Bulbasaur
        x = Bulbasaur()  # Creating an enemy for testing
        y = Bulbasaur()
        try:  # Testing calling of Bulbasaur calculate_damage_dealt method
            result = x.calculate_damage_dealt(y)
        except Exception as e:
            self.verificationErrors.append(f"Bulbasaur calculate damage dealt method failed: {str(e)}")
            return

        try:  # Testing if bulbasaur calculate_damage_dealt method returns the correct value
            assert result == 2, "Damage dealt should be 2"
        except Exception as x:
            self.verificationErrors.append(f"Bulbasaur calculate damage dealt method is incorrect: {str(x)}")

    def test_Bulbasaur_str(self):
        from pokemon import Bulbasaur
        bulbasaur = Bulbasaur()
        try:  # Testing calling of bulbasaur str method
            x = str(bulbasaur)
        except Exception as e:
            self.verificationErrors.append(f"Bulbasaur str method failed: {str(e)}")
            return

        try:  # Testing if bulbasaur str method returns the correct value
            assert x == "Bulbasaur's HP = 9 and level = 1"
        except AssertionError:
            self.verificationErrors.append(f"Bulbasaur str method is incorrect: {x}")

    def test_Squirtle_get_attack(self):
        from pokemon import Squirtle
        squirtle = Squirtle()
        try:  # Testing calling of squirtle get_attack method
            x = squirtle.get_attack()
        except Exception as e:
            self.verificationErrors.append(f"Squirtle get attack method failed: {str(e)}")
            return

        try:  # Testing if squirtle get_attack method returns the correct value
            assert x == 4, "Attack should be 4"
        except AssertionError:
            self.verificationErrors.append(f"Squirtle get attack method is incorrect: {str(x)}")

    def test_Squirtle_get_defence(self):
        from pokemon import Squirtle
        squirtle = Squirtle()
        try:  # Testing calling of squirtle get_defence method
            x = squirtle.get_defence()
        except Exception as e:
            self.verificationErrors.append(f"Squirlte get defence method failed: {str(e)}")
            return

        try:  # Testing if squirtle get_defence method returns the correct value
            assert x == 7, "defence should be 7"
        except AssertionError:
            self.verificationErrors.append(f"Squirlte get defence method is incorrect: {str(x)}")

    def test_Squirtle_get_speed(self):
        from pokemon import Squirtle
        squirtle = Squirtle()
        try:  # Testing calling of squirtle get_speed method
            x = squirtle.get_speed()
        except Exception as e:
            self.verificationErrors.append(f"Squirlte get speed method failed: {str(e)}")
            return

        try:  # Testing if squirtle get_speed method returns the correct value
            assert x == 7, "Speed should be 7"
        except AssertionError:
            self.verificationErrors.append(f"Squirtle get speed method is incorrect: {str(x)}")

    def test_Squirtle_get_name(self):
        from pokemon import Squirtle
        squirtle = Squirtle()
        try:  # Testing calling of squirtle get_name method
            x = squirtle.get_name()
        except Exception as e:
            self.verificationErrors.append(f"Squirtle get name method failed: {str(e)}")
            return

        try:  # Testing if squirtle get_name method returns the correct value
            assert x == "Squirtle", "Name should be Squirtle"
        except AssertionError:
            self.verificationErrors.append(f"Squirtle get name method is incorrect: {str(x)}")

    def test_Squirtle_set_level(self):
        from pokemon import Squirtle
        x = Squirtle()
        try:  # Testing calling of squirtle set_level method
            x.set_level(2)
        except Exception as e:
            self.verificationErrors.append(f"Squirtle set level method failed: {str(e)}")
            return

        try:  # Testing if squirtle set_level method is implemented correctly
            assert x.get_level() == 2, "Level should be 2"
            assert x.get_attack() == 5, "Attack should be 5"
            assert x.get_defence() == 8, "Speed should be 8"
        except Exception as x:
            self.verificationErrors.append(f"Squirtle set level method is incorrect: {str(x)}")

    def test_Squirtle_calculate_damage_dealt(self):
        from pokemon import Squirtle
        x = Squirtle()  # Creating enemy for testing
        y = Squirtle()
        try:  # Testing calling of squirtle calculate_damage_dealt method
            result = x.calculate_damage_dealt(y)
        except Exception as e:
            self.verificationErrors.append(f"Squirtle calculate damage dealt method failed: {str(e)}")
            return

        try:  # Testing if squirtle calculate_damage_dealt method returns the correct value
            assert result == 2, "Damage dealt should be 2"
        except Exception as x:
            self.verificationErrors.append(f"Squirtle calculate damage dealt method is incorrect: {str(x)}")

    def test_Squirtle_str(self):
        from pokemon import Squirtle
        squirtle = Squirtle()
        try:  # Testing calling of squirtle str method
            x = str(squirtle)
        except Exception as e:
            self.verificationErrors.append(f"Squirtle str method failed: {str(e)}")
            return

        try:  # Testing if Squirtle str method returns the correct value
            assert x == "Squirtle's HP = 8 and level = 1"
        except AssertionError:
            self.verificationErrors.append(f"Squirtle str method is incorrect: {x}")

    def test_MissingNo_get_attack(self):
        from pokemon import MissingNo
        missingno = MissingNo()
        try:  # Testing calling of MissingNo get_attack method
            x = missingno.get_attack()
        except Exception as e:
            self.verificationErrors.append(f"MissingNo get attack method failed: {str(e)}")
            return

        try:  # Testing if MissingNo get_attack method returns the correct value
            assert x == 6, "Attack should be 6"
        except AssertionError:
            self.verificationErrors.append(f"MissingNo get attack method is incorrect: {str(x)}")

    def test_MissingNo_get_defence(self):
        from pokemon import MissingNo
        missingno = MissingNo()
        try:  # Testing calling of MissingNo get_defence method
            x = missingno.get_defence()
        except Exception as e:
            self.verificationErrors.append(f"MissingNo get defence method failed: {str(e)}")
            return

        try:  # Testing if MissingNo get_defence method returns the correct value
            assert x == 6, "defence should be 6"
        except AssertionError:
            self.verificationErrors.append(f"MissingNo get defence method is incorrect: {str(x)}")

    def test_MissingNo_get_speed(self):
        from pokemon import MissingNo
        missingno = MissingNo()
        try:  # Testing calling of MissingNo get_speed method
            x = missingno.get_speed()
        except Exception as e:
            self.verificationErrors.append(f"MissingNo get speed method failed: {str(e)}")
            return

        try:  # Testing if MissingNo get_speed method returns the correct value
            assert x == 7, "Speed should be 7"
        except AssertionError:
            self.verificationErrors.append(f"MissingNo get speed method is incorrect: {str(x)}")

    def test_MissingNo_get_name(self):
        from pokemon import MissingNo
        missingno = MissingNo()
        try:  # Testing calling of MissingNo get_name method
            x = missingno.get_name()
        except Exception as e:
            self.verificationErrors.append(f"MissingNo get name method failed: {str(e)}")
            return

        try:  # Testing if MissingNo get_name method returns the correct value
            assert x == "MissingNo", "Name should be MissingNo"
        except AssertionError:
            self.verificationErrors.append(f"MissingNo get name method is incorrect: {str(x)}")

    def test_MissingNo_set_level(self):
        from pokemon import MissingNo
        x = MissingNo()
        try:  # Testing calling of MissingNo set_level method
            x.set_level(2)
        except Exception as e:
            self.verificationErrors.append(f"MissingNo set level method failed: {str(e)}")
            return

        try:  # Testing if MissingNo set_level method is implemented correctly
            assert x.get_level() == 2, "Level should be 2"
            assert x.get_attack() == 7, "Attack should be 7"
            assert x.get_hp() == 9, "HP should be 9"
            assert x.get_speed() == 8, "Speed should be 8"
            assert x.get_defence() == 7, "Defence should be 8"
        except Exception as x:
            self.verificationErrors.append(f"MissingNo set level method is incorrect: {str(x)}")

    def test_MissingNo_calculate_damage_dealt(self):
        from pokemon import MissingNo
        x = MissingNo()
        y = MissingNo()  # Creating enemy for testing
        try:  # Testing calling of MissingNo calculate_damage_dealt method
            result = x.calculate_damage_dealt(y)
        except Exception as e:
            self.verificationErrors.append(f"MissingNo calculate damage dealt method failed: {str(e)}")
            return

        try:  # Testing if MissingNo calculate_damage_dealt method returns the correct value
            assert result == 3 or result == 0, "Damage dealt should be 3"
        except Exception as x:
            self.verificationErrors.append(f"MissingNo calculate damage dealt method is incorrect: {str(x)}")

    def test_MissingNo_str(self):
        from pokemon import MissingNo
        missingno = MissingNo()
        try:  # Testing calling of MissingNo str method
            x = str(missingno)
        except Exception as e:
            self.verificationErrors.append(f"MissingNo str method failed: {str(e)}")
            return

        try:  # Testing if MissingNo str method returns the correct value
            assert x == "MissingNo's HP = 8 and level = 1"
        except AssertionError:
            self.verificationErrors.append(f"Squirtle str method is incorrect: {x}")


if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestPokemon)
    unittest.TextTestRunner(verbosity=0).run(suite)







