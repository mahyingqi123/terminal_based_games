"""
test_pokemon.py: Testing pokemon.py file
"""
import unittest
from poke_team import PokeTeam
from tester_base import TesterBase, captured_output

__author__ = "Mah Ying Qi"


class TestPokeTeam(TesterBase):

    def test_choose_team(self):
        team = PokeTeam("Name")
        try:  # Try setting battle mode to 3, should result in ValueError
            with self.assertRaises(ValueError):
                team.choose_team(3)
        except Exception as e:
            self.verificationErrors.append("Battle mode limit not handled correctly: "+str(e))
        try:  # Try setting battle mode to -1, should result in Value Error
            with self.assertRaises(ValueError):
                team.choose_team(-1)
        except Exception as e:
            self.verificationErrors.append("Battle mode limit not handled correctly: "+str(e))
        try:  # Try setting battle mode to 0, and input "1 1 4", team should be initialized
            with captured_output("1 1 4") as (inp,out,err):
                team.choose_team(0)
        except Exception as e:
            self.verificationErrors.append("Team cannot be chosen "+str(e))
            return

        output = out.getvalue().strip()  # Testing if output prompt is correct
        try:
            assert "Howdy Trainer! Choose your team as C B S\nwhere C is the number of Charmanders\n      B is the number of "\
                    "Bulbasaurs\n      S is the number of Squirtles" in output
        except AssertionError:
            self.verificationErrors.append("Prompt is incorrect: " + str(output))

        try:  # testing if battle mode is saved correctly
            assert team.battle_mode == 0
        except AssertionError:
            self.verificationErrors.append("Battle mode not saved correctly: battle mode is "+str(team.battle_mode))

        try:  # Try giving invalid criterion "Name", should result in ValueError
            with self.assertRaises(ValueError):
                team.choose_team(2,"Name")
        except Exception as e:
            self.verificationErrors.append("Criterion limit not handled correctly: "+str(e))
            return

        try:  # test with correct input "hp"
            with captured_output("1 1 1") as (inp,out,err):
                team.choose_team(2,'hp')
        except Exception as e:
            self.verificationErrors.append("Correct input given but error occured: " + str(e))
            return

        try:  # Testing if criterion is saved correctly
            assert team.criterion == "hp"
        except AssertionError:
            self.verificationErrors.append("Criterion not saved correctly: criterion is"+str(team.criterion))
            return

        with captured_output("1 2 3 0 0\n" # Try to input more than 4 different pokemons, should result in "Too many pokemons"
                             "a b c 1\n"  # Try to input non integer, should result in "Input should be integers"
                             "1 1 1 2\n"  # Try to input more 1 MissingNo, should result in "Too many MissingNo"
                             "10 0 0\n"  # Try to input more than 6 pokemons, should result in "Too many or too few pokemon count"
                             "1 1 3 1") as (inp, out, err):  # Try proper input and test result
            team.choose_team(0, 'hp')

        output = out.getvalue().strip()
        try:  # Testing if input error is handled correctly
            assert "Too many pokemons" in output
            assert "Input should be integers" in output
            assert "Too many MissingNo" in output
            assert "Too many pokemon count" in output
        except AssertionError:
            self.verificationErrors.append("User input error not handled correctly: " + str(output))

        try:  # Testing if team is chosen correctly
            assert str(team) == "Charmander's HP = 7 and level = 1, Bulbasaur's HP = 9 and level = 1, Squirtle's HP = " \
                                "8 and level = 1, Squirtle's HP = 8 and level = 1, Squirtle's HP = 8 and level = 1, " \
                                "MissingNo's HP = 8 and level = 1"
        except AssertionError:
            self.verificationErrors.append("Team not chosen correctly: "+str(team))
    def test_assign_team(self):

        from pokemon import MissingNo
        team = PokeTeam("Name")
        team.missingNo = MissingNo()
        team.battle_mode = 0
        try:    # Testing assign_team function with battle mode = 0
            team.assign_team(1,1,1)
        except Exception as e:
            self.verificationErrors.append("Team with battle mode 0 cannot be assigned: "+str(e))
            return
        try:  # Testing if team with battle mode = 0 is assigned correctly
            assert str(team) == "Charmander's HP = 7 and level = 1, Bulbasaur's HP = 9 and level = 1, Squirtle's HP = " \
                                "8 and level = 1, MissingNo's HP = 8 and level = 1"
        except AssertionError:
            self.verificationErrors.append("Team with battle mode 0 is not assigned correctly: " + str(team))

        team.battle_mode = 1
        try:  # Testing assign_team function with battle mode = 1
            team.assign_team(1,1,1)
        except Exception as e:
            self.verificationErrors.append("Team with battle mode 1 cannot be assigned: "+str(e))
            return
        try:  # Testing if team with battle mode = 1 is assigned correctly
            assert str(team) == "Charmander's HP = 7 and level = 1, Bulbasaur's HP = 9 and level = 1, Squirtle's HP = " \
                                "8 and level = 1, MissingNo's HP = 8 and level = 1"
        except AssertionError:
            self.verificationErrors.append("Team with battle mode 1 is not assigned correctly: " + str(team))

        team.battle_mode = 2
        team.criterion = 'hp'

        try:  # Testing assign_team function with battle mode = 2
            team.assign_team(1, 1, 1)
        except Exception as e:
            self.verificationErrors.append("Team with battle mode 2 cannot be assigned: " + str(e))
            return
        try:  # Testing if team with battle mode = 2 is assigned correctly
            assert str(team) == "Bulbasaur's HP = 9 and level = 1, Squirtle's HP = 8 and level = 1, Charmander's HP = " \
                                "7 and level = 1, MissingNo's HP = 8 and level = 1"
        except AssertionError:
            self.verificationErrors.append("Team with battle mode 2 is not assigned correctly: " + str(team))



if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestPokeTeam)
    unittest.TextTestRunner(verbosity=0).run(suite)







