Лабораторная работа 5.  Поиск текста на HTML-странице
==========================================================================================================================================
 
Разработать консольное приложение на Java.
 
Постановка задачи
------------------------------------------
 
Входные данные:
---------------------------------
Входной файл input1.html содержит текст, написанный на языке HTML.
В тесте находятся теги. В одной строке может быть несколько тегов. Теги находятся в угловых скобках, каждому открывающему тегу ставится в соответствие закрывающий тег. Например, пара тегов ```<b></b>```.
Между тегами находится текст, причем теги не разрывают текст. Например, при поиске слова hello комбинация ```h<b><i>el</i>l</b>o``` должна быть найдена.
Гарантируется,что страница HTML является корректной, т.е. все символы "<" и ">" используются только в тегах, все теги записаны корректно.
Входной файл input2.in содержит список фрагментов текста, которые нужно найти в первом файле, записанных через разделители (точка с запятой). Может быть несколько строк.
 
Примечание: 
---------------------
Ваша программа должна игнорировать различие между строчными и прописными буквами и для тегов и для искомого контекста. 
 
Выходные данные:
-----------------------------
1. В выходной файл output1.out вывести **список всех тегов в порядке возрастания количества символов тега**.
2. В выходной файл output2.out вывести номера строк (нумерация с 0) первого файла, в которых был найден искомый контекст в первый раз или -1 , если не был найден.
3. В выходной файл output3.out - список фрагментов второго файла, которые НЕ были найдены в первом файле.
