package Game.Entities.Dynamic;

import Game.Entities.Static.Apple;
import Main.GameSetUp;
import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.*;

import javax.swing.JFrame;

import Game.GameStates.State;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player {

	public int lenght;
	public boolean justAte;
	private Handler handler;

	public int xCoord;
	public int yCoord;

	public int moveCounter;
	public int steps; // cantidad de cuadros

    public String direction;//is your first name one?
    public String debugging;//Nueva variable de Debbuging


    public Player(Handler handler){
        this.handler = handler;
        xCoord = 0;
        yCoord = 0;
        moveCounter = 0;
        steps = 0;
        direction= "Right";
        debugging= "None";
        justAte = false;
        lenght= 1;

    }

	public void tick(){
		  moveCounter++;
	        //if(moveCounter>=5) {
	            checkCollisionAndMove();
	            debuggingCommand();
	            //checkApple(moveCounter);

	          // moveCounter=0;
	       // }
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
			State.setState(handler.getGame().pauseState);
			return;
		}
		
		

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
			if(!direction.equals("Down")) {
				direction="Up";	
			}
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
			if(!direction.equals("Up")) {
				direction="Down";
			}
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)){
			if(!direction.equals("Right")) {
				direction="Left";
			}
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)){
			if(!direction.equals("Left")) {
				direction="Right";
			}
		}
		
//      //Keys de Comandos
      if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)){
          debugging="N";
      }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_PLUS)) {
          debugging="Plus";
      }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)){
          debugging="Minus";
      }
      
	}
      
      //    Funcion para identificar comandos de Debbuging	
      public void debuggingCommand(){
          switch (debugging) {
              case "N":
                      AddTail(); 
                  break;
              case "Plus":
                  handler.getGame().PlusSpeed();
                  debugging="None";
                  break;
              case "Minus":
                  handler.getGame().MinusSpeed();
                  debugging="None";
                  break;
          }
      }
      
      //public void checkSteps() {
    	 //if (steps >= 30) {
    		//handler.getWorld().apple.setIsGood(false);
    			
    			// restar score
    			//quitar cola
    			
    		//}
    	//}
      

		

	

	public void checkCollisionAndMove(){
		handler.getWorld().playerLocation[xCoord][yCoord]=false;
		int x = xCoord;
		int y = yCoord;
		switch (direction){
		case "Left":
			if(xCoord==0){
				//kill();
				xCoord=handler.getWorld().GridWidthHeightPixelCount-1;
			}else{
				xCoord--;
			}
			break;
		case "Right":
			if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
				//kill();
				xCoord=0;

			}else{
				xCoord++;
			}
			break;
		case "Up":
			if(yCoord==0){
				//kill();
				yCoord=handler.getWorld().GridWidthHeightPixelCount-1;

			}else{
				yCoord--;
			}
			break;
		case "Down":
			if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
				//kill();
				yCoord=0;
			}else{
				yCoord++;
			}
			break;
		}
		if(handler.getWorld().playerLocation[xCoord][yCoord]==true) {
			kill();
		}
		handler.getWorld().playerLocation[xCoord][yCoord]=true;


		if(handler.getWorld().appleLocation[xCoord][yCoord]){
			Eat();
		}

		if(!handler.getWorld().body.isEmpty()) {
			handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
			handler.getWorld().body.removeLast();
			handler.getWorld().body.addFirst(new Tail(x, y,handler));
		}

	}

	public void render(Graphics g,Boolean[][] playeLocation){
		Random r = new Random();
		for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
			for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
				g.setColor(Color.GREEN);

				if(playeLocation[i][j]||handler.getWorld().appleLocation[i][j]){
					g.fillRect((i*handler.getWorld().GridPixelsize),
							(j*handler.getWorld().GridPixelsize),
							handler.getWorld().GridPixelsize,
							handler.getWorld().GridPixelsize);
				}

			}
		}


	}
	
	 //Funcion para anadir Cola
	    public void AddTail(){
	        lenght++;
	        Tail tail= null;
	        switch (direction){
	            case "Left":
	                if( handler.getWorld().body.isEmpty()){
	                    if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
	                        tail = new Tail(this.xCoord+1,this.yCoord,handler);
	                    }else{
	                        if(this.yCoord!=0){
	                            tail = new Tail(this.xCoord,this.yCoord-1,handler);
	                        }else{
	                            tail =new Tail(this.xCoord,this.yCoord+1,handler);
	                        }
	                    }
	                }else{
	                    if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
	                        tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
	                    }else{
	                        if(handler.getWorld().body.getLast().y!=0){
	                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
	                        }else{
	                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

	                        }
	                    }

	                }
	                break;
	            case "Right":
	                if( handler.getWorld().body.isEmpty()){
	                    if(this.xCoord!=0){
	                        tail=new Tail(this.xCoord-1,this.yCoord,handler);
	                    }else{
	                        if(this.yCoord!=0){
	                            tail=new Tail(this.xCoord,this.yCoord-1,handler);
	                        }else{
	                            tail=new Tail(this.xCoord,this.yCoord+1,handler);
	                        }
	                    }
	                }else{
	                    if(handler.getWorld().body.getLast().x!=0){
	                        tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
	                    }else{
	                        if(handler.getWorld().body.getLast().y!=0){
	                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
	                        }else{
	                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
	                        }
	                    }

	                }
	                break;
	            case "Up":
	                if( handler.getWorld().body.isEmpty()){
	                    if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
	                        tail=(new Tail(this.xCoord,this.yCoord+1,handler));
	                    }else{
	                        if(this.xCoord!=0){
	                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
	                        }else{
	                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
	                        }
	                    }
	                }else{
	                    if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
	                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
	                    }else{
	                        if(handler.getWorld().body.getLast().x!=0){
	                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
	                        }else{
	                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
	                        }
	                    }

	                }
	                break;
	            case "Down":
	                if( handler.getWorld().body.isEmpty()){
	                    if(this.yCoord!=0){
	                        tail=(new Tail(this.xCoord,this.yCoord-1,handler));
	                    }else{
	                        if(this.xCoord!=0){
	                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
	                        }else{
	                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
	                        } System.out.println("Tu biscochito");
	                    }
	                }else{
	                    if(handler.getWorld().body.getLast().y!=0){
	                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
	                    }else{
	                        if(handler.getWorld().body.getLast().x!=0){
	                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
	                        }else{
	                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
	                        }
	                    }

	                }
	                break;
	        }
	        handler.getWorld().body.addLast(tail);
	        debugging = "None";

	    }

	

	public void Eat(){
		lenght++;
		Tail tail= null;
		handler.getWorld().appleLocation[xCoord][yCoord]=false;
		handler.getWorld().appleOnBoard=false;
		switch (direction){
		case "Left":
			if( handler.getWorld().body.isEmpty()){
				if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
					tail = new Tail(this.xCoord+1,this.yCoord,handler);
				}else{
					if(this.yCoord!=0){
						tail = new Tail(this.xCoord,this.yCoord-1,handler);
					}else{
						tail =new Tail(this.xCoord,this.yCoord+1,handler);
					}
				}
			}else{
				if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
					tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
				}else{
					if(handler.getWorld().body.getLast().y!=0){
						tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
					}else{
						tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

					}
				}

			}
			break;
		case "Right":
			if( handler.getWorld().body.isEmpty()){
				if(this.xCoord!=0){
					tail=new Tail(this.xCoord-1,this.yCoord,handler);
				}else{
					if(this.yCoord!=0){
						tail=new Tail(this.xCoord,this.yCoord-1,handler);
					}else{
						tail=new Tail(this.xCoord,this.yCoord+1,handler);
					}
				}
			}else{
				if(handler.getWorld().body.getLast().x!=0){
					tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
				}else{
					if(handler.getWorld().body.getLast().y!=0){
						tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
					}else{
						tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
					}
				}

			}
			break;
		case "Up":
			if( handler.getWorld().body.isEmpty()){
				if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
					tail=(new Tail(this.xCoord,this.yCoord+1,handler));
				}else{
					if(this.xCoord!=0){
						tail=(new Tail(this.xCoord-1,this.yCoord,handler));
					}else{
						tail=(new Tail(this.xCoord+1,this.yCoord,handler));
					}
				}
			}else{
				if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
					tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
				}else{
					if(handler.getWorld().body.getLast().x!=0){
						tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
					}else{
						tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
					}
				}

			}
			break;
		case "Down":
			if( handler.getWorld().body.isEmpty()){
				if(this.yCoord!=0){
					tail=(new Tail(this.xCoord,this.yCoord-1,handler));
				}else{
					if(this.xCoord!=0){
						tail=(new Tail(this.xCoord-1,this.yCoord,handler));
					}else{
						tail=(new Tail(this.xCoord+1,this.yCoord,handler));
					} System.out.println("Tu biscochito");
				}
			}else{
				if(handler.getWorld().body.getLast().y!=0){
					tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
				}else{
					if(handler.getWorld().body.getLast().x!=0){
						tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
					}else{
						tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
					}
				}

			}
			break;
		}
		handler.getWorld().body.addLast(tail);
		handler.getWorld().playerLocation[tail.x][tail.y] = true;

		handler.getGame().changetime();
		handler.getGame().changescore();
		System.out.println("player line");
	}


	public void kill(){
		lenght = 0;
		for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
			for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

				handler.getWorld().playerLocation[i][j]=false;
			}

		}
		handler.getGame().gameOver();
	}


	public boolean isJustAte() {
		return justAte;
	}

	public void setJustAte(boolean justAte) {
		this.justAte = justAte;
	}
}
