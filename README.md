# job4j_threads
Многозадачность
Изучаем тему многопоточность
Многозадачность и параллелизм.

В ОС за выполнение задач отвечает процессор. Чем больше процессоров имеет компьютер, тем больше задач может одновременно выполнить компьютер.

Один процессор может одновременно выполнять только одну задачу. Задача выполняемая процессором называется процесс. То есть если в вашем компьютере два процессора, то он может выполнять два процесса параллельно. Процессы не могут обмениваться между собой данными. Это не зависимые элементы.

А как же старые компьютеры спросите Вы? Там же и окна были и winamp, можно послушать и всего один процессор был.

Все верно, было такое. Здесь нужно различать понятие параллелизм и многозадачность. Любая программа состоит из операторов, которые превращаются в список инструкций для процессора. Процессор для каждой программы выделает квант времени. 5 мс - 100 мс - для человека такой временной промежуток незаметен. Это дает возможность одному процессору выполнять часть инструкций одной задачи и переключаться на выполнение другой. Для пользователя такое переключение не заметно, а с другой стороны создается иллюзия параллельного выполнения. Поэтому старые компьютеры поддерживали многозадачность, а не параллелизм. 

Виртуальная машина позволяет программистам создавать такие задачи, которые могут выполняться в многозадачном режиме. То есть часть задачи выполнится, а потом переключится на другую часть задачи и так пока все инструкции в этих задачах не будут выполнены. 

Такие задачи называются потоками исполнения или нить исполнения (от английского слова Thread). Как я уже писал процессы не могут взаимодействовать между собой и один процессор может иметь только один процесс. В свою очередь нити исполнения - это абстракция над процессом исполнения. Нитей исполнения можно создать сколько угодно. Так же нити могут обмениваться между собой данными.

Если сказать проще, процессор имеет только один процесс, а сам процесс может иметь множество нитей
