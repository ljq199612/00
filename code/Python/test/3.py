'''


'''

import random

welcome = 'welcome to Heroes world!'

mapmsg = '#######'
mapins = "\n-*- the world is like this -*- \n %s \n the '*' is you " % (mapmsg,)
mapp = ['#','#','#','#','#','#','#']

instruction = '''
control your hero:
================================
| 'a' for left || 'd' for right|
================================
'''

print(welcome)
name = input('input your name: ')
hp = 50

if not name:
    name = 'player01'
usermsg = {'name': name, 'hp' : hp}

print("your hero's name is :", usermsg['name'], 'hp is ：', usermsg['hp'])
print(mapins, instruction)

def apple(hp):
    hp += 10
    return hp

def bomb(hp):
    hp -= 10
    return hp

eventlist = [apple, bomb]
point = 0
while 1:
    mapp[point] = "*"
    print('you are here', "".join(mapp))
    userinput = input('go or quit : ')

    if userinput == 'd' and point < 6 :
        mapp[point] = '#'
        point += 1
        usermsg['hp']= random.choice(eventlist)(usermsg['hp'])
        print(usermsg['hp'])
    elif userinput == 'a' and point > 0 :
        mapp[point] = '#'
        point -= 1
        usermsg['hp']= random.choice(eventlist)(usermsg['hp'])
        print(usermsg['hp'])
    elif userinput == 'quit' :
        print("goodbye!")
        break
    else :  print(instruction)
