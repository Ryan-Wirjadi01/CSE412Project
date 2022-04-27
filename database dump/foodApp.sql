PGDMP                         z            foodApp    14.1    14.1     %           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            &           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            '           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            (           1262    16395    foodApp    DATABASE     T   CREATE DATABASE "foodApp" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';
    DROP DATABASE "foodApp";
                postgres    false            �            1259    16651 	   cook_food    TABLE     �   CREATE TABLE public.cook_food (
    fid integer NOT NULL,
    rid integer NOT NULL,
    rating integer,
    name character(2083),
    price numeric(4,2)
);
    DROP TABLE public.cook_food;
       public         heap    postgres    false            �            1259    16423    customer    TABLE     �   CREATE TABLE public.customer (
    pay_information integer NOT NULL,
    address character(2083),
    name character(2083),
    cid integer NOT NULL
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    16592    delivers    TABLE     |   CREATE TABLE public.delivers (
    did integer NOT NULL,
    order_id integer NOT NULL,
    customer_id integer NOT NULL
);
    DROP TABLE public.delivers;
       public         heap    postgres    false            �            1259    16409    deliverydriver    TABLE     o   CREATE TABLE public.deliverydriver (
    did integer NOT NULL,
    rating integer,
    name character(2083)
);
 "   DROP TABLE public.deliverydriver;
       public         heap    postgres    false            �            1259    16663    has    TABLE        CREATE TABLE public.has (
    fid integer NOT NULL,
    rid integer NOT NULL,
    order_id integer,
    customer_id integer
);
    DROP TABLE public.has;
       public         heap    postgres    false            �            1259    16580    order_place    TABLE     9  CREATE TABLE public.order_place (
    status character varying(20),
    restaurant_rating integer,
    driver_rating integer,
    order_id integer NOT NULL,
    food_rating integer,
    "timestamp" character varying,
    customer_id integer NOT NULL,
    item_quantity integer,
    item_price double precision
);
    DROP TABLE public.order_place;
       public         heap    postgres    false            �            1259    16614 
   restaurant    TABLE     �   CREATE TABLE public.restaurant (
    rating integer NOT NULL,
    location character(2083),
    name character(2083),
    rid integer NOT NULL,
    cuisine character(20)
);
    DROP TABLE public.restaurant;
       public         heap    postgres    false            !          0    16651 	   cook_food 
   TABLE DATA                 public          postgres    false    214   �"                 0    16423    customer 
   TABLE DATA                 public          postgres    false    210   t*                 0    16592    delivers 
   TABLE DATA                 public          postgres    false    212   }+                 0    16409    deliverydriver 
   TABLE DATA                 public          postgres    false    209   ,       "          0    16663    has 
   TABLE DATA                 public          postgres    false    215   �,                 0    16580    order_place 
   TABLE DATA                 public          postgres    false    211   =-                  0    16614 
   restaurant 
   TABLE DATA                 public          postgres    false    213   �.       �           2606    16657    cook_food cook_food_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.cook_food
    ADD CONSTRAINT cook_food_pkey PRIMARY KEY (fid, rid);
 B   ALTER TABLE ONLY public.cook_food DROP CONSTRAINT cook_food_pkey;
       public            postgres    false    214    214            �           2606    16429    customer customer_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (cid);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    210            �           2606    16596    delivers delivers_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.delivers
    ADD CONSTRAINT delivers_pkey PRIMARY KEY (did, order_id, customer_id);
 @   ALTER TABLE ONLY public.delivers DROP CONSTRAINT delivers_pkey;
       public            postgres    false    212    212    212            ~           2606    16415 "   deliverydriver deliverydriver_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.deliverydriver
    ADD CONSTRAINT deliverydriver_pkey PRIMARY KEY (did);
 L   ALTER TABLE ONLY public.deliverydriver DROP CONSTRAINT deliverydriver_pkey;
       public            postgres    false    209            �           2606    16667    has has_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.has
    ADD CONSTRAINT has_pkey PRIMARY KEY (fid, rid);
 6   ALTER TABLE ONLY public.has DROP CONSTRAINT has_pkey;
       public            postgres    false    215    215            �           2606    16586    order_place order_place_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.order_place
    ADD CONSTRAINT order_place_pkey PRIMARY KEY (order_id, customer_id);
 F   ALTER TABLE ONLY public.order_place DROP CONSTRAINT order_place_pkey;
       public            postgres    false    211    211            �           2606    16620    restaurant restaurant_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.restaurant
    ADD CONSTRAINT restaurant_pkey PRIMARY KEY (rid);
 D   ALTER TABLE ONLY public.restaurant DROP CONSTRAINT restaurant_pkey;
       public            postgres    false    213            �           2606    16658    cook_food cook_food_rid_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.cook_food
    ADD CONSTRAINT cook_food_rid_fkey FOREIGN KEY (rid) REFERENCES public.restaurant(rid);
 F   ALTER TABLE ONLY public.cook_food DROP CONSTRAINT cook_food_rid_fkey;
       public          postgres    false    213    3462    214            �           2606    16597    delivers delivers_did_fkey    FK CONSTRAINT        ALTER TABLE ONLY public.delivers
    ADD CONSTRAINT delivers_did_fkey FOREIGN KEY (did) REFERENCES public.deliverydriver(did);
 D   ALTER TABLE ONLY public.delivers DROP CONSTRAINT delivers_did_fkey;
       public          postgres    false    212    3454    209            �           2606    16602 +   delivers delivers_order_id_customer_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.delivers
    ADD CONSTRAINT delivers_order_id_customer_id_fkey FOREIGN KEY (order_id, customer_id) REFERENCES public.order_place(order_id, customer_id);
 U   ALTER TABLE ONLY public.delivers DROP CONSTRAINT delivers_order_id_customer_id_fkey;
       public          postgres    false    212    212    211    211    3458            �           2606    16668    has has_fid_rid_fkey    FK CONSTRAINT     ~   ALTER TABLE ONLY public.has
    ADD CONSTRAINT has_fid_rid_fkey FOREIGN KEY (fid, rid) REFERENCES public.cook_food(fid, rid);
 >   ALTER TABLE ONLY public.has DROP CONSTRAINT has_fid_rid_fkey;
       public          postgres    false    3464    214    214    215    215            �           2606    16673 !   has has_order_id_customer_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.has
    ADD CONSTRAINT has_order_id_customer_id_fkey FOREIGN KEY (order_id, customer_id) REFERENCES public.order_place(order_id, customer_id);
 K   ALTER TABLE ONLY public.has DROP CONSTRAINT has_order_id_customer_id_fkey;
       public          postgres    false    215    211    211    3458    215            �           2606    16587 (   order_place order_place_customer_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_place
    ADD CONSTRAINT order_place_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customer(cid) ON DELETE CASCADE;
 R   ALTER TABLE ONLY public.order_place DROP CONSTRAINT order_place_customer_id_fkey;
       public          postgres    false    3456    211    210            !   �  x���Mo�8��{?O�������S�m�oI��3�=-��	ˢ��ɤ�~EI��{�@����� oH�Ϗ��w�[��v{'��!��80��ߝ1��y�ÑH�d���H��F"Iu�~\}���F�<	$��x;���V��&�-�{�g  �zoGb1^.�盏.�cV�Ǽl��IF�x�jEs  �V����Y{̫�(�~�I�$n���^"  ��=��ť����}���
ŵ���{�  `8�}���}���q�$��W>�^!  ��=��c:����AG��J��<j�2��X  �;���d���E���A�h  ��=t�8��y�����y�&�)  xV�ǅ���~8u��ن�^\�   ����0>�*>fe|�e�(��   /�S������Ģ����ޙ4�R��#  *e|,��6m��.�{�H���Ale`�^*  ��=���r���协���d��H��T  0 ��=��=6�T��*�T��y  ��������Z֑���  T���t������>�R��S�+  Cb�������˦M|l�I��8��)��W	  �u|̻�8H-V2ӑ��H�}�  B}��Y{xm{\�:K�Ħ��x_���^+  �_5W�]{�m{�u�d�D���  ��7�L�F|���ѯW�_**�Rb#��Q��  �SƇ?��<ti��F��8�/�db��  *�M��M��=6��~��Q��]  xͪ˦NǪ���X��AF�j�M @�l�����K��Ku.3�  g�]ܵ�߶ǵL�=x�  ��}����K����L�J|0������Z�b��}�U[  ^-�����[�M|�ezRYY�T�P�t���  ����}˶i�k�a��c�����  �ի'��|��{~�E�u�`�{�U��2 �@�C������Ǭ��z�z5�T�}/  ���K��1o�/�٤J�bs�i.6��ǾW  zeG�.Ƈ���SdJ�M.sc7@�{  �*>.�g���q}0Irv��^-  ��+���k>v٦2Թ6����]�;��}�  �������Ǭ����yT�  8g�c�>�]}|��t'ާ*bk�  Pq]^Wݞo� �����>T�����[�v��{�  ` l}L��t$�u|\�T���u�}  �Z����^:]>o[g[O;�g��$����  ����z��c���JgA��28~K�O}/  ���a|xm|��88�����Q�8t  V�]�6>����ŽL�K���� �׮���]|�1���>�b�ַ*UJ{  ��>.��������yJ"���P  0�i[��Ӧ>>�(z�D�*�u�+  �Pև�p�����.�{mߖ��@��Q���  �TƇ7^:������eh��xo�Z3�  T칋���7N����N��� �Aq��ˬ���R;�#k��G�J�#�� �+�>�]}l�_�{qo���l ���>��;;x��A�hn� �s�����cm���q��  ���c�x�鬙3&#�+�CK�*�T�\>  M|,�.o�6�q+���  x����py��y��T���Be2�Q$�^*  ������WeyH�>�<j  ,���d�p:����I&~el{  �F5a}��҇�q���5�Q�Y!��?�k  P�[�����Ӧ>�aK(�fW��H  0 �˶���c�������  @�n}�o}�=s��~�r.� �Ju�Û8��Q�w��  g���.ߵ�?��2�Qߋ  �s���.�=nT���hF����o|n  �w�M��'|�]{�U���Ě�   �j�˱�쾇�}�r�3À  ��C>��>VJ���!>  ���a��u}\Rs�  |���ˬ���A��x�{�  `P��X�'��c���g��!U&Ӿ	  ����p�w���б�3  а�>&����c�s�$��  ^p|�c���.��:8��P  0��.Ք�7o�s�o         �   x����j�@@�Oqw� M4�������m�,S��gDM!o�iɢ���ֳ�ww�L�϶�\�}�*���TwŹl�;����Q�/�5j��	D�e��>�HQ���_޶��ë@f�2N�ٺ��<��K  ��L�)�%k�{  0!."�7�RDW.?��:�Gk
ە�.c�
  &��Jq��J�B�Y���  S�2aq�+E-�q�Z���u��  S����VF�,�   �s����~ �I�         �   x���v
Q���W((M��L�KI��,K-*V�H�L�Q�/JI-���K�K�s�M�0G�P�`CҴ���(#��Q�:
 D���3����F�R�UT
v�Q���%��24��Y�J��dehD�oh�# ���         �   x��ֿ�@�����gS�%0ۚ
.� �]�#������c���gy�ݞ���2?W�Eu���t�]۹��_�ǃ�8#���7�P�6�uw��%�T�	�Cx>ADDD�'J�JOj��`Zco��BDDD�3�52AZ#���{-��J� �S      "   n   x���v
Q���W((M��L��H,V�H�L�Q(�E)�E� VriqI~.��������a�� E��\���f�� ET2͐��C5L�c��`B%�M� r W�n         �  x�ݔ�N�0��}
�M@J#ό_X�`Q	��m[�&�HmSR�H�������h��ğ��3�����g����m�V�2���l���bY�ӝ[�v����Om�ظy�p��%eES����n[U�칮���'�Z����Iʖ����}Y�����������t�T��=^\=\ޱӤ(W�'e��LĀ��S�SN�
�2O�"���<;�̎mS;�Ǔ10�/x`Z)��H>� ��)F��}0D>L%�܏q����M��7��͔C� �n��y��p1Ŝ��-�η!D������Q��~W��UCXiQY؛4����A�F�:~�!������SU�U���4ZM�ATc���e���<�)�OR��0$5޻�=��&ӡ)i� p#FI�I���Oj�)���(I� ����{o5��%P轓���          �  x����j�@�Ὧbv��)M��@V>BB��t9������H��jz-��Έ��F�Z��}�`������n�g�ǹ���O�._�|H��d�J���Tf�>��qI��lOX�U=��eO$��ڪ�x��1���˞8���N	  �='!&�:ٜ��9�7=  h��CRXI����[�u�;74  �����e��   �BL�I�N��D��G�h.�ki�F��.  h��\L��Ȧ  mb��n�NZ���q�\  �����1_h盞  �L�	_�Zsq�n  P-6��b �6MO  Z$Ą����c���%.��R��  ��\�F[���Q�v.Ϛ�	  �B�	�P�oUz���7�J�E~�j� @+�������  �,^.�1D�I�]dR��"��   ��bz;C�Z)�v�',  �(Vq5� UroG-?;�QT  �BqHg�V����   ���E�c��3:��c��  T(nFM���҈���|�5=  h�X]\�u��%�  �j������}c�k��  �����y�
OZe�u(�*=ʪ�  P!Vc#��Һ��  -��������  �9)nkWb�����31�+�x  ���uLg��h� @���.9+��7=  h�j=�S: @��\��V[Go  ����B<�7y��{��t� L���     