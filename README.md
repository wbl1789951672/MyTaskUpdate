# myTaskupdate

> 1. User类中取消了hashmap数据结构，将原有数据封装为一个Description类
> 2. 将后台返回的数据封装成一个Msg类，其中包含状态码，提示信息，服务器返回给浏览器的数据，前台根据这个对象的数据，做出相应的反应。
> 3. 从前台提交的数据，取消了复杂的逻辑映射
> 4. 加入了对未映射的URL的处理，返回Msg对象的JSON数据
> 5. 任务二中的用户添加，存在id冲突的问题，之前的解决办法是对id相同的数据进行更新，现在的处理是提示插入失败，无法插入。
> 6. 将个别未私有化的属性私有化
> 7. 更改目录结构，目录结构更清晰
> 8. 更改某些类或属性的命名，使之命名更加规范
