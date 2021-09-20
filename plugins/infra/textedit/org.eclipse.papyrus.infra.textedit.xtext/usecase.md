#Papyrus XTextEditor
The goal is to open an XtextEditor for an existing grammar in the SashEditor. So we need to provide the same level of integration than Diagram and Table Editor
The save is specific for this editor, because we edit a text and not directly the model. So we need to save the text inside the model on CTRL-S and on "focus lost".
For the "focus lost", we must consider several case:
1. Papyrus editor is closed
2. Eclipse is closed
3. The user click outside of the Papyrus editor (ModelExplorer, Property Views/...) 

   We use the SaveTextOnFocusLostPartListener
4. The PapyrusXtextEditor is closed

   Custom implementation of the dispose method (close method is never called by Papyrus)
5. We have several PapyrusXtextEditor open side by side, we must save the first one each time the user click in another one
   we use the SaveTextOnFocusLostPageLifeCycleEventsListener